class AuthManager {
    constructor() {
        this.currentUser = null;
        this.init();
    }

    init() {
        this.checkAuthState();
        this.bindEvents();
        this.fixModalIssues();
        this.handleUrlParams();
    }

    fixModalIssues() {
        // Отключаем стандартное поведение Bootstrap для модалок
        document.addEventListener('show.bs.modal', () => {
            document.body.classList.add('modal-open');
            document.body.style.paddingRight = '0';
        });

        document.addEventListener('hidden.bs.modal', () => {
            document.body.classList.remove('modal-open');
            document.body.style.paddingRight = '0';
        });

        // Предотвращаем сдвиг контента
        const originalGetScrollbarWidth = bootstrap.Modal.prototype._getScrollbarWidth;
        bootstrap.Modal.prototype._getScrollbarWidth = function() {
            return 0;
        };
    }

    checkAuthState() {
        const token = localStorage.getItem('authToken');

        if (token) {
            this.currentUser = { name: 'User', email: 'user@example.com' };
            this.updateUI(true);

            if (!window.location.pathname.includes('authDS.html')) {
                window.location.href = '/';
            }
        } else {
            this.updateUI(false);
        }
    }

    handleUrlParams() {
        const urlParams = new URLSearchParams(window.location.search);
        const tabParam = urlParams.get('tab');

        if (tabParam === 'register') {
            this.switchToRegisterTab();
        } else if (tabParam === 'login') {
            this.switchToLoginTab();
        }
    }

    switchToRegisterTab() {
        const registerTab = document.getElementById('register-tab');
        const registerPane = document.getElementById('register');

        if (registerTab && registerPane) {
            // Убираем активный класс с вкладки входа
            const loginTab = document.getElementById('login-tab');
            const loginPane = document.getElementById('login');

            loginTab.classList.remove('active');
            loginPane.classList.remove('show', 'active');

            // Добавляем активный класс на вкладку регистрации
            registerTab.classList.add('active');
            registerPane.classList.add('show', 'active');
        }
    }

    // Метод для переключения на вкладку входа
    switchToLoginTab() {
        const loginTab = document.getElementById('login-tab');
        const loginPane = document.getElementById('login');

        if (loginTab && loginPane) {
            // Убираем активный класс с вкладки регистрации
            const registerTab = document.getElementById('register-tab');
            const registerPane = document.getElementById('register');

            registerTab.classList.remove('active');
            registerPane.classList.remove('show', 'active');

            // Добавляем активный класс на вкладку входа
            loginTab.classList.add('active');
            loginPane.classList.add('show', 'active');
        }
    }
    updateUI(isLoggedIn) {
        const userDropdown = document.querySelector('.nav-link.dropdown-toggle');
        if (userDropdown) {
            const span = userDropdown.querySelector('span');
            if (span) {
                span.textContent = isLoggedIn ? this.currentUser.name : 'Войти';
            }
        }
    }

    bindEvents() {
        // Обработчик формы входа
        const loginForm = document.getElementById('loginForm');
        if (loginForm) {
            loginForm.addEventListener('submit', (e) => {
                e.preventDefault();
                this.handleLogin();
            });
        }

        // Обработчик формы регистрации
        const registerForm = document.getElementById('registerForm');
        if (registerForm) {
            registerForm.addEventListener('submit', (e) => {
                e.preventDefault();
                this.handleRegistration();
            });
        }
        const loginTab = document.getElementById('login-tab');
        const registerTab = document.getElementById('register-tab');

        if (loginTab) {
            loginTab.addEventListener('click', () => {
                this.updateUrlParam('login');
            });
        }

        if (registerTab) {
            registerTab.addEventListener('click', () => {
                this.updateUrlParam('register');
            });
        }

        // Обработчик сброса пароля
        const forgotPasswordLink = document.getElementById('forgotPasswordLink');
        if (forgotPasswordLink) {
            forgotPasswordLink.addEventListener('click', (e) => {
                e.preventDefault();
                this.showResetPasswordModal();
            });
        }

        // Обработчик формы сброса пароля
        const resetPasswordForm = document.getElementById('resetPasswordForm');
        if (resetPasswordForm) {
            resetPasswordForm.addEventListener('submit', (e) => {
                e.preventDefault();
                this.handlePasswordReset();
            });
        }

        // Предотвращаем закрытие dropdown при клике внутри
        const dropdown = document.querySelector('.custom-dropdown');
        if (dropdown) {
            dropdown.addEventListener('click', (e) => {
                e.stopPropagation();
            });
        }
    }
    updateUrlParam(tab) {
        const url = new URL(window.location);
        url.searchParams.set('tab', tab);
        window.history.replaceState({}, '', url);
    }

    handleLogin() {
        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;
        const rememberMe = document.getElementById('rememberMe').checked;

        // Валидация
        if (!this.validateEmail(email)) {
            this.showMessage('Пожалуйста, введите корректный email', 'error');
            return;
        }

        if (password.length < 6) {
            this.showMessage('Пароль должен содержать не менее 6 символов', 'error');
            return;
        }

        // Имитация запроса на сервер
        this.showMessage('Выполняется вход...', 'info');

        setTimeout(() => {
            // В реальном приложении здесь будет запрос к API
            const token = 'mock_jwt_token_' + Date.now();
            localStorage.setItem('authToken', token);
            this.currentUser = { name: 'User', email: email };
            this.updateUI(true);
            this.showMessage('Вход выполнен успешно!', 'success');

            // Перенаправление на главную страницу
            setTimeout(() => {
                window.location.href = 'indexDS.html';
            }, 1000);
        }, 1500);
    }

    async handleRegistration() {
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const email = document.getElementById('registerEmail').value;
        const username = document.getElementById('registerUsername').value;
        const password = document.getElementById('registerPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const agreeTerms = document.getElementById('agreeTerms').checked;

        // Валидация
        if (!firstName || !lastName) {
            this.showMessage('Пожалуйста, введите имя и фамилию', 'error');
            return;
        }

        if (!this.validateEmail(email)) {
            this.showMessage('Пожалуйста, введите корректный email', 'error');
            return;
        }
        if (!username) {
            this.showMessage('Пожалуйста, введите логин', 'error');
            return;
        }

        if (password.length < 6) {
            this.showMessage('Пароль должен содержать не менее 6 символов', 'error');
            return;
        }

        if (password !== confirmPassword) {
            this.showMessage('Пароли не совпадают', 'error');
            return;
        }

        if (!agreeTerms) {
            this.showMessage('Необходимо согласиться с условиями использования', 'error');
            return;
        }

        this.showMessage('Регистрация выполняется...', 'info');

        const userData = {
            firstName: firstName.trim(),
            lastName: lastName.trim(),
            email: email.trim(),
            username: username.trim(),
            password: password
        };

        try {
            this.showMessage('Регистрация выполняется...', 'info');

            // Отправка запроса на сервер
            const response = await fetch('/auth/local/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData)
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || `Ошибка сервера: ${response.status}`);
            }

            const userDTO = await response.json();

            // Сохраняем токен (если он возвращается в ответе)
            if (userDTO.token) {
                localStorage.setItem('authToken', userDTO.token);
            }

            // Сохраняем данные пользователя
            this.currentUser = {
                id: userDTO.id,
                name: `${userDTO.firstName} ${userDTO.lastName}`,
                username: userDTO.username,
                email: userDTO.email
            };

            this.updateUI(true);
            this.showMessage('Регистрация выполнена успешно!', 'success');

            // Перенаправление на главную страницу
            setTimeout(() => {
                window.location.href = '/';
            }, 1000);

        } catch (error) {
            console.error('Ошибка регистрации:', error);
            this.showMessage(error.message || 'Произошла ошибка при регистрации', 'error');
        }
    }

    showResetPasswordModal() {
        const resetModal = new bootstrap.Modal(document.getElementById('resetPasswordModal'));
        resetModal.show();
    }

    handlePasswordReset() {
        const email = document.getElementById('resetEmail').value;

        if (!this.validateEmail(email)) {
            this.showMessage('Пожалуйста, введите корректный email', 'error');
            return;
        }

        // Имитация запроса на сервер
        this.showMessage('Отправка инструкций...', 'info');

        setTimeout(() => {
            const resetModal = bootstrap.Modal.getInstance(document.getElementById('resetPasswordModal'));
            resetModal.hide();
            this.showMessage('Инструкции по сбросу пароля отправлены на ваш email', 'success');
        }, 1500);
    }

    validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    showMessage(message, type = 'info') {
        // Создаем элемент для сообщения
        const messageEl = document.createElement('div');
        messageEl.className = `alert alert-${type === 'error' ? 'danger' : type} alert-dismissible fade show`;
        messageEl.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;

        // Добавляем сообщение в начало формы
        const authCard = document.querySelector('.auth-card');
        authCard.insertBefore(messageEl, authCard.firstChild);

        // Автоматически скрываем сообщение через 5 секунд
        setTimeout(() => {
            if (messageEl.parentNode) {
                messageEl.remove();
            }
        }, 5000);
    }
}

// Инициализация при загрузке страницы
document.addEventListener('DOMContentLoaded', () => {
    window.authManager = new AuthManager();

    // Добавляем класс для отключения скролла
    document.body.classList.add('no-scroll');
});