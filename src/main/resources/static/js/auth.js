let currentUser = null;
document.addEventListener("DOMContentLoaded", function () {
    displayCurrentUser();
})
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
    window.Telegram.Login.auth(
        {
            bot_id: "ChurchSong_bot",
            request_access: true
        },
        async (user) => {
            if (!user) {
                console.error("Telegram auth failed");
                return;
            }

            const response = await fetch('/auth/telegram', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                credentials: 'include',
                body: JSON.stringify(user)
            });

            if (response.ok) {
                await displayCurrentUser();
            }
        }
    );
}