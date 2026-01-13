// let currentUserBands = [];
// document.addEventListener("DOMContentLoaded", function () {
//     renderCurrentUsersBands();
// })
//
// async function getCurrentUserBands() {
//     if(currentUser) {
//         try {
//             currentUserBands = apiGet(`/bands/by-user/?user-id=${currentUser.id}`)
//         } catch (error) {
//             console.error('Ошибка при получении групп пользователя:', error);
//             return [];
//         }
//     }
// }
//
// function renderCurrentUsersBands() {
//     if (currentUser) {
//         currentUserBands = getCurrentUserBands();
//         const currentUserBandsTable = document.getElementById('currentUserBands')
//         currentUserBandsTable.innerHTML = '';
//         currentUserBands.forEach(band => {
//             const row = document.createElement('tr');
//             row.innerHTML = `
//                 <td>${band.id}</td>
//                 <td>${band.name}</td>
//             `;
//             currentUserBandsTable.appendChild(row);
//         })
//     }
// }