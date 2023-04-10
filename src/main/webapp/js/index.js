const getLocationButton = document.getElementById("get-location");
const findWifiButton = document.getElementById("find-wifi");
const tableBody = document.querySelector("table tbody");
let latitude = document.getElementById("latitude").value;
let longitude = document.getElementById("longitude").value;


getLocationButton.addEventListener("click", () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            latitude = position.coords.latitude;
            longitude = position.coords.longitude;
            document.getElementById("latitude").value = latitude;
            document.getElementById("longitude").value = longitude;
        }, (error) => {
            console.error(error.message);
        });
    } else {
        console.error("이 브라우저에서는 해당 기능을 지원하지 않습니다.");
    }
});

findWifiButton.addEventListener("click", () => {
    latitude = document.getElementById("latitude").value;
    longitude = document.getElementById("longitude").value;
    fetch("/getWifiData.do?latitude=" + latitude + "&longitude=" + longitude)
        .then(response => response.json())
        .then(data => {
            // WifiLogServlet을 호출하여 데이터베이스에 로그 추가
            fetch("/getWifiLog.do?latitude=" + latitude + "&longitude=" + longitude)
                .then(response => response.json())
                .then(data => console.log(data))
                .catch(error => console.error(error));
            tableBody.innerHTML = "";
            if (data.length > 0) {
                data.forEach(record => {
                    const row = document.createElement("tr");
                    const distance = document.createElement("td");
                    distance.textContent = record.distance.toFixed(2);
                    const controlNumber = document.createElement("td");
                    controlNumber.textContent = record.X_SWIFI_MGR_NO;
                    const borough = document.createElement("td");
                    borough.textContent = record.X_SWIFI_WRDOFC;
                    const wifiName = document.createElement("a");
                    wifiName.href = "#" ;
                    wifiName.textContent = record.X_SWIFI_MAIN_NM;
                    const wifiNameCell = document.createElement("td");
                    wifiNameCell.appendChild(wifiName);
                    const roadNameAddress = document.createElement("td");
                    roadNameAddress.textContent = record.X_SWIFI_ADRES1;
                    const detailedAddress = document.createElement("td");
                    detailedAddress.textContent = record.X_SWIFI_ADRES2;
                    const installationFloor = document.createElement("td");
                    installationFloor.textContent = record.X_SWIFI_INSTL_FLOOR;
                    const installationType = document.createElement("td");
                    installationType.textContent = record.X_SWIFI_INSTL_TY;
                    const installer = document.createElement("td");
                    installer.textContent = record.X_SWIFI_INSTL_MBY;
                    const serviceClassification = document.createElement("td");
                    serviceClassification.textContent = record.X_SWIFI_SVC_SE;
                    const networkType = document.createElement("td");
                    networkType.textContent = record.X_SWIFI_CMCWR;
                    const installationYear = document.createElement("td");
                    installationYear.textContent = record.X_SWIFI_CNSTC_YEAR;
                    const indoorOutdoorClassification = document.createElement("td");
                    indoorOutdoorClassification.textContent = record.X_SWIFI_INOUT_DOOR;
                    const wifiConnectionEnvironment = document.createElement("td");
                    wifiConnectionEnvironment.textContent = record.X_SWIFI_REMARS3;
                    const latitude = document.createElement("td");
                    latitude.textContent = record.LAT.toFixed(6);
                    const longitude = document.createElement("td");
                    longitude.textContent = record.LNT.toFixed(6);
                    const workDate = document.createElement("td");
                    workDate.textContent = record.WORK_DTTM;

                    row.appendChild(distance);
                    row.appendChild(controlNumber);
                    row.appendChild(borough);
                    row.appendChild(wifiNameCell);
                    row.appendChild(roadNameAddress);
                    row.appendChild(detailedAddress);
                    row.appendChild(installationFloor);
                    row.appendChild(installationType);
                    row.appendChild(installer);
                    row.appendChild(serviceClassification);
                    row.appendChild(networkType);
                    row.appendChild(installationYear);
                    row.appendChild(indoorOutdoorClassification);
                    row.appendChild(wifiConnectionEnvironment);
                    row.appendChild(latitude);
                    row.appendChild(longitude);
                    row.appendChild(workDate);

                    tableBody.appendChild(row);

                    wifiName.addEventListener('click', () => {
                        const url = "ViewDetails.jsp" +
                            "?distance=" + encodeURIComponent(record.distance.toFixed(2)) +
                            "&controlNumber=" + encodeURIComponent(record.X_SWIFI_MGR_NO) +
                            "&borough=" + encodeURIComponent(record.X_SWIFI_WRDOFC) +
                            "&wifiName=" + encodeURIComponent(record.X_SWIFI_MAIN_NM) +
                            "&roadNameAddress=" + encodeURIComponent(record.X_SWIFI_ADRES1) +
                            "&detailedAddress=" + encodeURIComponent(record.X_SWIFI_ADRES2) +
                            "&installationFloor=" + encodeURIComponent(record.X_SWIFI_INSTL_FLOOR) +
                            "&installationType=" + encodeURIComponent(record.X_SWIFI_INSTL_TY) +
                            "&installer=" + encodeURIComponent(record.X_SWIFI_INSTL_MBY) +
                            "&serviceClassification=" + encodeURIComponent(record.X_SWIFI_SVC_SE) +
                            "&networkType=" + encodeURIComponent(record.X_SWIFI_CMCWR) +
                            "&installationYear=" + encodeURIComponent(record.X_SWIFI_CNSTC_YEAR) +
                            "&indoorOutdoorClassification=" + encodeURIComponent(record.X_SWIFI_INOUT_DOOR) +
                            "&wifiConnectionEnvironment=" + encodeURIComponent(record.X_SWIFI_REMARS3) +
                            "&latitude=" + encodeURIComponent(record.LAT.toFixed(6)) +
                            "&longitude=" + encodeURIComponent(record.LNT.toFixed(6)) +
                            "&workDate=" + encodeURIComponent(record.WORK_DTTM);
                        window.location.href = url;
                    });
                });
            } else {
                const row = document.createElement("tr");
                const message = document.createElement("td");
                message.textContent = "근처에 WIFI 정보가 없습니다.";
                message.colSpan = 17;
                message.style.textAlign = "center";
                row.appendChild(message);
                tableBody.appendChild(row);
            }
        })
        .catch(error => console.error(error));










});
