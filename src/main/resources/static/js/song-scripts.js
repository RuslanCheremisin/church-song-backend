/* ====== Church Song Unified Script ====== */
document.addEventListener("DOMContentLoaded", () => {
    const navbar = document.querySelector(".custom-navbar");
    const modals = document.querySelectorAll(".modal");

    // === Scroll-based navbar effect ===
    window.addEventListener("scroll", () => {
        if (window.scrollY > 50) {
            navbar.classList.add("scrolled");
        } else {
            navbar.classList.remove("scrolled");
        }
    });

    // === Smooth scroll for internal links ===
    document.querySelectorAll('a[href^="#"]').forEach(link => {
        link.addEventListener("click", function (e) {
            const target = document.querySelector(this.getAttribute("href"));
            if (target) {
                e.preventDefault();
                window.scrollTo({
                    top: target.offsetTop - 60,
                    behavior: "smooth"
                });
            }
        });
    });

    // === Bootstrap modal fixes ===
    // Отключаем костыль из auth, т.к. теперь скролл стабильный
    if (typeof bootstrap !== "undefined") {
        bootstrap.Modal.prototype._getScrollbarWidth = function () {
            return window.innerWidth - document.documentElement.clientWidth;
        };
    }

    modals.forEach(modal => {
        modal.addEventListener("show.bs.modal", () => {
            document.body.classList.add("modal-open");
        });
        modal.addEventListener("hidden.bs.modal", () => {
            document.body.classList.remove("modal-open");
        });
    });

    // === Button ripple effect (optional, unify UX) ===
    document.querySelectorAll(".btn-primary, .btn-outline-primary").forEach(btn => {
        btn.addEventListener("click", e => {
            const ripple = document.createElement("span");
            ripple.classList.add("ripple");
            ripple.style.left = e.offsetX + "px";
            ripple.style.top = e.offsetY + "px";
            btn.appendChild(ripple);
            setTimeout(() => ripple.remove(), 600);
        });
    });
});

/* === Ripple effect styles === */
const style = document.createElement("style");
style.textContent = `
.ripple {
    position: absolute;
    border-radius: 50%;
    background: rgba(255, 255, 255, 0.4);
    width: 10px;
    height: 10px;
    transform: scale(0);
    animation: ripple-animation 0.6s linear;
    pointer-events: none;
}
@keyframes ripple-animation {
    to {
        transform: scale(20);
        opacity: 0;
    }
}`;
document.head.appendChild(style);
