class AuthManager {
    constructor() {
        this.currentUser = null;
        this.init();
    }

    init() {
        this.checkAuthState();
        this.bindEvents();
    }

    checkAuthState() {
        const token = localStorage.getItem('authToken');
        if (token) {
            this.currentUser = { name: 'User', email: 'user@example.com' };
            this.updateUI(true);
        } else {
            this.updateUI(false);
        }
    }

    updateUI(isLoggedIn) {
        const loginBtn = document.getElementById('loginBtn');
        const registerBtn = document.getElementById('registerBtn');
        const userProfileBtn = document.getElementById('userProfileBtn');
        const logoutBtn = document.getElementById('logoutBtn');

        loginBtn.style.display = isLoggedIn ? 'none' : 'block';
        registerBtn.style.display = isLoggedIn ? 'none' : 'block';
        userProfileBtn.style.display = isLoggedIn ? 'block' : 'none';
        logoutBtn.style.display = isLoggedIn ? 'block' : 'none';
    }

    bindEvents() {
        document.getElementById('loginBtn').addEventListener('click', (e) => {
            e.preventDefault();
            alert('Login will be implemented soon.');
        });

        document.getElementById('registerBtn').addEventListener('click', (e) => {
            e.preventDefault();
            alert('Registration will be implemented soon.');
        });

        document.getElementById('logoutBtn').addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.removeItem('authToken');
            this.currentUser = null;
            this.updateUI(false);
            alert('Logged out.');
        });

        document.getElementById('userProfileBtn').addEventListener('click', (e) => {
            e.preventDefault();
            alert('User profile page is under development.');
        });
    }
}

class VideoController {
    constructor() {
        this.video = document.getElementById('bgVideo');
        this.init();
    }

    init() {
        if (!this.video) return;

        this.video.addEventListener('loadeddata', () => {
            this.video.play().catch(() => {
                console.warn('Autoplay blocked, showing play button.');
                this.showPlayButton();
            });
        });

        this.video.addEventListener('error', () => {
            this.useFallbackBackground();
        });
    }

    showPlayButton() {
        const playButton = document.createElement('button');
        playButton.textContent = 'Play Background Video';
        playButton.className = 'btn btn-primary mt-3';
        playButton.onclick = () => {
            this.video.play();
            playButton.remove();
        };
        document.querySelector('.cta-buttons').appendChild(playButton);
    }

    useFallbackBackground() {
        document.querySelector('.video-background').style.background =
            'linear-gradient(135deg, #142850, #27496D, #0C7B93, #00A8CC)';
    }
}

class NavigationManager {
    constructor() {
        this.bindNavigation();
    }

    bindNavigation() {
        document.getElementById('exploreSongsBtn').addEventListener('click', () => {
            window.location.href = '/songs';
        });

        document.getElementById('createBandBtn').addEventListener('click', () => {
            if (window.authManager.currentUser) {
                window.location.href = '/bands?create=true';
            } else {
                alert('Please log in to create a band.');
            }
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    window.authManager = new AuthManager();
    window.videoController = new VideoController();
    window.navigationManager = new NavigationManager();
});
