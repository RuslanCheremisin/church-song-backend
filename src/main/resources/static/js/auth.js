let currentUser = null;
let currentUserBands = [];
let allBands = [];
let bandById = null;

document.addEventListener("DOMContentLoaded", function () {
    initializeUserData();
    const currentBandId = sessionStorage.getItem("currentBandId");
    if(currentBandId) {
        getBandById(currentBandId);
    }
})

async function initializeUserData(){
    await displayCurrentUser();
    await getCurrentUserBands();
    renderCurrentUsersBands();
    await getAllBands();
    renderAllBands();
}
async function apiGet(url) {
    try {
        const headers = {
            'Content-Type': 'application/json'
        };
        const response = await fetch(url, {
            headers,
            credentials: 'include', // Важно для отправки cookies
        });

        if(!response.ok) {
            const error = new Error(`HTTP error! status: ${response.status}`)
            throw error;
        }
        return response.status === 204 ? null : await response.json();

    } catch (error) {
        console.error('API call failed:', error)
        throw error;
    }

}

async function displayCurrentUser() {
    toggleLogoutElement();
    let currentUserField = document.getElementById("currentUser");

    currentUser = await apiGet('/auth/current-user')
    if (currentUser.id === -1) {
        currentUserField.innerHTML = 'войдите или зарегистрируйтесь'
    } else {
        currentUserField.innerHTML = currentUser.firstName + " " + currentUser.lastName;
        hideLoginElements();
    }

}

function hideLoginElements() {
    const loginButton = document.getElementById("loginButton");
    if (loginButton != null) {
        loginButton.hidden = true;
    }
    const loginContainer = document.getElementById("loginContainer");
    if (loginContainer != null) {
        loginContainer.hidden = true;
    }
    const loginHeader = document.getElementById("loginHeader");
    if (loginHeader != null) {
        loginHeader.hidden = true;
    }
    const loginWOtherStuffHeader = document.getElementById("loginWOtherStuffHeader");
    if (loginWOtherStuffHeader != null) {
        loginWOtherStuffHeader.hidden = true;
    }
    const loginWithOtherStuffContainer = document.getElementById("loginWithOtherStuffContainer");
    if (loginWithOtherStuffContainer != null) {
        loginWithOtherStuffContainer.hidden = true;
    }
    toggleLogoutElement();

}
function toggleLogoutElement() {
    const logoutElement = document.getElementById("logoutElement")
    if (logoutElement != null) {
        logoutElement.hidden ? logoutElement.hidden = false : logoutElement.hidden = true;
    }
}

function loginWithTelegram() {
    // window.Telegram.Login.auth(
    //     {
    //         bot_id: "ChurchSong_bot",
    //         request_access: true
    //     },
    //     async (user) => {
    //         if (!user) {
    //             console.error("Telegram auth failed");
    //             return;
    //         }
    //
    //         const response = await fetch('/auth/telegram', {
    //             method: 'POST',
    //             headers: {'Content-Type': 'application/json'},
    //             credentials: 'include',
    //             body: JSON.stringify(user)
    //         });
    //
    //         if (response.ok) {
    //             await displayCurrentUser();
    //         }
    //     }
    // );
}
async function getCurrentUserBands() {
    if(currentUser.id !== -1) {
        try {
            currentUserBands = await apiGet(`/bands/by-user/${currentUser.id}`);
        } catch (error) {
            console.error('Ошибка при получении групп пользователя:', error);
            return [];
        }
    }
}

function renderCurrentUsersBands() {
    if (currentUser) {
        const currentUserBandsTable = document.getElementById('currentUserBands')
        if (currentUserBands) {
            currentUserBands.forEach(band => {
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${band.id}</td>
                <td><a href="/bands/byId/${band.id}" onclick="saveBandId(${band.id})">${band.name}<a/></td>
            `;
                currentUserBandsTable.appendChild(row);
            })
        }
    }
}

async function getAllBands() {
        try {
            allBands = await apiGet(`/bands`);
        } catch (error) {
            console.error('Ошибка при получении всех групп:', error);
            return [];
        }
}
function renderAllBands() {
    if (currentUser) {
        const allBandsTable = document.getElementById('allBands')
        if (allBands) {
            allBands.forEach(band => {
                const row = document.createElement('tr');
                row.innerHTML = `
                <td>${band.id}</td>
                <td><a href="/bands/byId/${band.id}" onclick="saveBandId(${band.id})">${band.name}<a/></td>
            `;
                allBandsTable.appendChild(row);
            })
        }
    }
}

async function handleCreateBand() {
    if (currentUser) {
        const photoFile = document.getElementById('photo');

        const formData = new FormData();
        formData.append('name', document.getElementById('name').value);
        formData.append('email', document.getElementById('email').value);
        formData.append('contactPhone', document.getElementById('contactPhone').value);
        formData.append('bio', document.getElementById('bio').value);

        if (photoFile.files.length > 0) {
            formData.append('photoFile', photoFile.files[0])
        }

        for (let [key, value] of formData.entries()) {
            console.log(`${key}: ${value}`);
        }

        try {
            const response = await fetch('/bands', {
                method: 'POST',
                // headers: {
                //     'Content-Type': 'application/json',
                // },
                // body: JSON.stringify(formData)
                body: formData
            });

            if (response.ok) {
                alert('Группа добавлена!');
                document.getElementById('name').value = '';
                document.getElementById('email').value = '';
                document.getElementById('contactPhone').value = '';
                document.getElementById('bio').value = '';
                document.getElementById('photoFile').value = '';
            } else {
                alert('Группа не добавлена! Проверьте логи');
            }
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }
}

function saveBandId(bandId) {
    sessionStorage.setItem('currentBandId', bandId);
}

// document.addEventListener('DOMContentLoaded', function () {
//     const currentBandId = sessionStorage.getItem("currentBndId");
//     if(currentBandId) {
//         getBandById(currentBandId);
//     }
// });

async function getBandById(bandId) {
    try {
        bandById = await apiGet(`/bands/${bandId}`);
    } catch (error) {
        alert("error getting bandById. " + error.value)
    }
    if (bandById) {
        document.getElementById('bandName').innerHTML = bandById.name;
        document.getElementById('bandBio').innerHTML = bandById.bio;
        document.getElementById('bandLeader').innerHTML = bandById.leader.firstName;
        document.getElementById('bandPhoto').src = bandById.bandAvatar.link;
    }
}


