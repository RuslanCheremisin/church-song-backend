class LoginManager {
    constructor() {
        this.currentForm = 'login';
        this.init();
    }

    init() {
        this.bindEvents();
        this.checkAuthState();
    }

    checkAuthState() {
        // Проверяем, не авторизован ли уже пользователь
        const token = localStorage.getItem('authToken');
        if (token) {
            // Если пользователь уже авторизован, перенаправляем на главную
            window.location.href = '/';
        }
    }

    bindEvents() {
        // Переключение между формами
        document.getElementById('showRegisterLink').addEventListener('click', (e) => {
            e.preventDefault();
            this.showForm('register');
        });

        document.getElementById('forgotPasswordLink').addEventListener('click', (e) => {
            e.preventDefault();
            this.showForm('reset');
        });

        document.getElementById('backToLoginLink').addEventListener('click', (e) => {
            e.preventDefault();
            this.showForm('login');
        });

        document.getElementById('backToLoginFromResetLink').addEventListener('click', (e) => {
            e.preventDefault();
            this.showForm('login');
        });

        // Обработка отправки форм
        document.getElementById('loginForm').addEventListener('submit', (e) => {
            e.preventDefault();
            this.handleLogin();
        });

        document.getElementById('registerForm').addEventListener('submit', (e) => {
            e.preventDefault();
            this.handleRegister();
        });

        document.getElementById('resetPasswordForm').addEventListener('submit', (e) => {
            e.preventDefault();
            this.handlePasswordReset();
        });
    }

    showForm(formType) {
        // Скрываем все формы
        document.querySelectorAll('.auth-form').forEach(form => {
            form.classList.add('d-none');
        });

        // Показываем нужную форму
        if (formType === 'login') {
            document.getElementById('loginForm').classList.remove('d-none');
            document.querySelector('.card-footer').style.display = 'block';
            this.currentForm = 'login';
        } else if (formType === 'register') {
            document.getElementById('registerForm').classList.remove('d-none');
            document.querySelector('.card-footer').style.display = 'none';
            this.currentForm = 'register';
        } else if (formType === 'reset') {
            document.getElementById('resetPasswordForm').classList.remove('d-none');
            document.querySelector('.card-footer').style.display = 'none';
            this.currentForm = 'reset';
        }
    }

    handleLogin() {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const rememberMe = document.getElementById('rememberMe').checked;

        // Валидация
        if (!this.validateEmail(email) && !this.validateUsername(email)) {
            this.showAlert('Пожалуйста, введите корректный email или имя пользователя', 'error');
            return;
        }

        if (password.length < 6) {
            this.showAlert('Пароль должен содержать не менее 6 символов', 'error');
            return;
        }

        // Имитация запроса на сервер
        this.showAlert('Выполняется вход...', 'info');

        setTimeout(() => {
            // В реальном приложении здесь будет запрос к API
            const token = 'mock_jwt_token_' + Date.now();
            localStorage.setItem('authToken', token);

            if (rememberMe) {
                localStorage.setItem('rememberMe', 'true');
            }

            this.showAlert('Вход выполнен успешно!', 'success');

            // Перенаправление на главную страницу
            setTimeout(() => {
                window.location.href = '/';
            }, 1000);
        }, 1500);
    }

    handleRegister() {
        const name = document.getElementById('regName').value;
        const email = document.getElementById('regEmail').value;
        const password = document.getElementById('regPassword').value;
        const confirmPassword = document.getElementById('regConfirmPassword').value;

        // Валидация
        if (name.length < 2) {
            this.showAlert('Имя должно содержать не менее 2 символов', 'error');
            return;
        }

        if (!this.validateEmail(email)) {
            this.showAlert('Пожалуйста, введите корректный email', 'error');
            return;
        }

        if (password.length < 6) {
            this.showAlert('Пароль должен содержать не менее 6 символов', 'error');
            return;
        }

        if (password !== confirmPassword) {
            this.showAlert('Пароли не совпадают', 'error');
            return;
        }

        // Имитация запроса на сервер
        this.showAlert('Регистрация...', 'info');

        setTimeout(() => {
            // В реальном приложении здесь будет запрос к API
            this.showAlert('Регистрация завершена успешно!', 'success');

            // Автоматический вход после регистрации
            setTimeout(() => {
                const token = 'mock_jwt_token_' + Date.now();
                localStorage.setItem('authToken', token);
                window.location.href = '/';
            }, 1000);
        }, 1500);
    }

    handlePasswordReset() {
        const email = document.getElementById('resetEmail').value;

        if (!this.validateEmail(email)) {
            this.showAlert('Пожалуйста, введите корректный email', 'error');
            return;
        }

        // Имитация запроса на сервер
        this.showAlert('Отправка ссылки для сброса пароля...', 'info');

        setTimeout(() => {
            // В реальном приложении здесь будет запрос к API
            this.showAlert('Ссылка для сброса пароля отправлена на ваш email', 'success');

            // Возврат к форме входа
            setTimeout(() => {
                this.showForm('login');
            }, 2000);
        }, 1500);
    }

    validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    validateUsername(username) {
        // Простая проверка имени пользователя (только буквы, цифры, подчеркивания)
        const re = /^[a-zA-Z0-9_]{3,30}$/;
        return re.test(username);
    }

    showAlert(message, type) {
        // Удаляем существующие алерты
        const existingAlert = document.querySelector('.custom-alert');
        if (existingAlert) {
            existingAlert.remove();
        }

        // Создаем новый алерт
        const alert = document.createElement('div');
        alert.className = `custom-alert alert alert-${type === 'error' ? 'danger' : type === 'success' ? 'success' : 'info'} mt-3`;
        alert.textContent = message;
        alert.style.borderRadius = '8px';

        // Добавляем алерт в карточку
        document.querySelector('.card-body').prepend(alert);

        // Автоматически скрываем алерт через 5 секунд
        setTimeout(() => {
            if (alert.parentNode) {
                alert.remove();
            }
        }, 5000);
    }
}

// Инициализация при загрузке страницы
document.addEventListener('DOMContentLoaded', () => {
    window.loginManager = new LoginManager();
});