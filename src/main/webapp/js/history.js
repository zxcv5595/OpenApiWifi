const tableBody = document.querySelector('#tableBody');
fetch('/HistoryGet.do')
    .then(response => response.json())
    .then(data => {
        tableBody.innerHTML = "";
        if (data.length > 0) {
            data.forEach(item => {
                const row = document.createElement('tr');
                const idCell = document.createElement('td');
                const xCell = document.createElement('td');
                const yCell = document.createElement('td');
                const dateCell = document.createElement('td');
                const deleteBtnCell = document.createElement('td'); // 삭제 버튼이 들어갈 td 엘리먼트 생성
                const deleteBtn = document.createElement('button');
                deleteBtn.textContent = '삭제';
                deleteBtn.addEventListener('click', () => {
                    const id = idCell.textContent;
                    console.log(id);
                    fetch("/delete.do?id="+ id )
                        .then(response => {
                            if (response.ok) {
                                location.reload();
                            } else {
                                alert('데이터 삭제 중 오류가 발생했습니다.');
                            }
                        })
                        .catch(error => console.error(error));
                });
                idCell.textContent = item.id;
                xCell.textContent = item.x;
                yCell.textContent = item.y;
                dateCell.textContent = item.date;
                deleteBtnCell.appendChild(deleteBtn); // 삭제 버튼이 들어간 td 엘리먼트 추가
                row.appendChild(idCell);
                row.appendChild(xCell);
                row.appendChild(yCell);
                row.appendChild(dateCell);
                row.appendChild(deleteBtnCell); // 삭제 버튼이 들어간 td 엘리먼트 추가
                tableBody.appendChild(row);
            });
        } else {
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            cell.colSpan = 5;
            cell.textContent = '히스토리가 없습니다.';
            row.appendChild(cell);
            tableBody.appendChild(row);
        }
    })
    .catch(error => console.error(error));
