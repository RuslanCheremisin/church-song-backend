// Глобальные переменные
let selectedFiles = {
    video: null,
    audio: null,
    images: [],
    text: ''
};

let currentEditingPartId = null;
let partModal, songModal, originalModal;

// Инициализация при загрузке страницы
document.addEventListener('DOMContentLoaded', function() {
    initializeModals();
    initializeYouTubePlayers();
    initializeEventListeners();
    initializeSidebar();
});

// Инициализация модальных окон
function initializeModals() {
    partModal = new bootstrap.Modal(document.getElementById('partModal'));
    songModal = new bootstrap.Modal(document.getElementById('songModal'));
    originalModal = new bootstrap.Modal(document.getElementById('originalModal'));
}

// Инициализация сайдбара
function initializeSidebar() {
    const firstFolder = document.querySelector('.folder-item');
    if (firstFolder) {
        firstFolder.classList.add('active');
    }
}

// Переключение папок в сайдбаре
function toggleFolder(folderHeader) {
    const folderItem = folderHeader.parentElement;
    folderItem.classList.toggle('active');
}

// Инициализация YouTube плееров
function initializeYouTubePlayers() {
    // Основное видео (оригинальная запись)
    if (document.getElementById('youtube-player')) {
        new YT.Player('youtube-player', {
            height: '315',
            width: '100%',
            videoId: 'dQw4w9WgXcQ',
            playerVars: {
                'playsinline': 1
            }
        });
    }

    // Видео для разбора партии
    if (document.getElementById('tutorial-video')) {
        new YT.Player('tutorial-video', {
            height: '315',
            width: '100%',
            videoId: 'dQw4w9WgXcQ',
            playerVars: {
                'playsinline': 1
            }
        });
    }
}

// Инициализация обработчиков событий
function initializeEventListeners() {
    // Сброс формы при закрытии модального окна
    document.getElementById('partModal').addEventListener('hidden.bs.modal', function() {
        resetForm();
        currentEditingPartId = null;
    });

    // Обработка формы оригинальной записи
    document.getElementById('originalForm').addEventListener('submit', function(e) {
        e.preventDefault();
        saveOriginalRecording();
    });

    // Обработка формы песни
    document.getElementById('songForm').addEventListener('submit', function(e) {
        e.preventDefault();
        saveSongData();
    });

    // Обработка формы партии
    document.getElementById('partForm').addEventListener('submit', function(e) {
        e.preventDefault();
        savePart();
    });

    // Обработка кликов по песням в сайдбаре
    document.querySelectorAll('.sub-list .nav-item').forEach(item => {
        item.addEventListener('click', function(e) {
            document.querySelectorAll('.sub-list .nav-item').forEach(song => {
                song.classList.remove('active');
            });
            this.classList.add('active');

            const songName = this.querySelector('.song-name').textContent;
            console.log('Выбрана песня:', songName);
        });
    });
}

// Переключение видимости контента партии
function togglePart(titleElement) {
    const partCard = titleElement.closest('.part-card');
    const wasActive = partCard.classList.contains('active');

    document.querySelectorAll('.part-card').forEach(card => {
        card.classList.remove('active');
    });

    if (!wasActive) {
        partCard.classList.add('active');
    }
}

// Открытие модального окна для добавления партии
function addPart() {
    document.getElementById('partModalTitle').textContent = 'Добавить новую партию';
    document.getElementById('partSubmitBtn').textContent = 'Создать партию';
    currentEditingPartId = null;
    partModal.show();
}

// Редактирование партии
function editPart(partId) {
    const partCard = document.querySelector(`[data-part-id="${partId}"]`);
    const partTitle = partCard.querySelector('.part-title h3').textContent;
    const [instrument, role] = partTitle.split(' - ');

    document.getElementById('partInstrument').value = getInstrumentValue(instrument);
    document.getElementById('partRole').value = role;

    document.getElementById('partModalTitle').textContent = 'Редактировать партию';
    document.getElementById('partSubmitBtn').textContent = 'Сохранить изменения';
    currentEditingPartId = partId;
    partModal.show();
}

// Удаление партии
function deletePart(partId) {
    if (confirm('Вы уверены, что хотите удалить эту партию?')) {
        const partCard = document.querySelector(`[data-part-id="${partId}"]`);
        partCard.remove();
        showToast('Партия успешно удалена', 'success');
    }
}

// Редактирование песни
function editSong() {
    songModal.show();
}

// Редактирование оригинальной записи
function editOriginal() {
    // Заполняем форму текущими значениями
    const originalContent = document.querySelector('.original-content');
    const youtubePlayer = originalContent.querySelector('#youtube-player');
    const audioPlayer = originalContent.querySelector('.audio-player');

    // Здесь можно добавить логику заполнения формы текущими данными
    originalModal.show();
}

// Удаление оригинальной записи
function deleteOriginal() {
    if (confirm('Вы уверены, что хотите удалить оригинальную запись?')) {
        const originalSection = document.querySelector('.original-recording');
        originalSection.remove();
        showToast('Оригинальная запись удалена', 'success');
    }
}

// Сохранение данных песни
function saveSongData() {
    const title = document.getElementById('songTitle').value;
    const key = document.getElementById('songKey').value;
    const bpm = document.getElementById('songBPM').value;
    const timeSignature = document.getElementById('songTimeSignature').value;

    document.querySelector('.song-info h1').textContent = title;
    document.querySelector('.song-meta').innerHTML = `
        <span class="badge bg-secondary">Тональность: ${key}</span>
        <span class="badge bg-secondary">Темп: ${bpm} BPM</span>
        <span class="badge bg-secondary">Размер: ${timeSignature}</span>
    `;

    songModal.hide();
    showToast('Данные песни успешно обновлены', 'success');
}

// Сохранение оригинальной записи
function saveOriginalRecording() {
    const originalContent = document.querySelector('.original-content');
    let newContent = '';

    // YouTube видео
    const enableYouTube = document.getElementById('enableYouTube').checked;
    const youtubeUrl = document.getElementById('youtubeUrl').value;

    if (enableYouTube && youtubeUrl) {
        const videoId = extractYouTubeId(youtubeUrl);
        newContent += `
            <div class="media-section mb-4">
                <h5 class="media-title">🎥 YouTube видео</h5>
                <div class="youtube-player" id="youtube-player"></div>
            </div>
        `;
    }

    // Загруженное видео
    const enableVideoUpload = document.getElementById('enableVideoUpload').checked;
    const videoFile = document.getElementById('videoFileOriginal').files[0];

    if (enableVideoUpload && videoFile) {
        const videoUrl = URL.createObjectURL(videoFile);
        newContent += `
            <div class="media-section mb-4">
                <h5 class="media-title">📹 Загруженное видео</h5>
                <div class="video-wrapper">
                    <video controls class="w-100" style="max-width: 560px; border-radius: 8px;">
                        <source src="${videoUrl}" type="${videoFile.type}">
                        Ваш браузер не поддерживает видео элементы.
                    </video>
                </div>
                <div class="video-info d-flex justify-content-between mt-2">
                    <span>${videoFile.name}</span>
                    <span>${(videoFile.size / (1024 * 1024)).toFixed(1)} MB</span>
                </div>
            </div>
        `;
    }

    // Аудио запись
    const enableAudioUpload = document.getElementById('enableAudioUpload').checked;
    const audioFile = document.getElementById('audioFileOriginal').files[0];

    if (enableAudioUpload && audioFile) {
        const audioUrl = URL.createObjectURL(audioFile);
        newContent += `
            <div class="media-section">
                <h5 class="media-title">🎵 Аудио запись</h5>
                <div class="audio-player">
                    <audio controls class="audio-element w-100">
                        <source src="${audioUrl}" type="${audioFile.type}">
                        Ваш браузер не поддерживает аудио элементы.
                    </audio>
                    <div class="audio-info d-flex justify-content-between mt-2">
                        <span>${audioFile.name}</span>
                        <span>${(audioFile.size / (1024 * 1024)).toFixed(1)} MB</span>
                    </div>
                </div>
            </div>
        `;
    }

    // Обновляем контент
    originalContent.innerHTML = newContent;

    // Переинициализируем YouTube плеер если нужно
    if (enableYouTube && youtubeUrl) {
        setTimeout(() => {
            new YT.Player('youtube-player', {
                height: '315',
                width: '100%',
                videoId: extractYouTubeId(youtubeUrl),
                playerVars: {
                    'playsinline': 1
                }
            });
        }, 100);
    }

    originalModal.hide();
    showToast('Оригинальная запись обновлена', 'success');
}

// Извлечение ID из YouTube URL
function extractYouTubeId(url) {
    if (!url) return 'dQw4w9WgXcQ'; // default fallback

    const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
    const match = url.match(regExp);
    return (match && match[7].length === 11) ? match[7] : url;
}

// Вспомогательная функция для получения значения инструмента
function getInstrumentValue(instrumentText) {
    const instrumentMap = {
        '🎸 Акустическая гитара': 'guitar',
        '🎥 Электрогитара': 'guitar',
        '🎵 Бас': 'bass',
        '👤 Вокал': 'vocal',
        '🥁 Ударные': 'drums',
        '🎹 Клавиши': 'keys'
    };
    return instrumentMap[instrumentText] || 'other';
}

// Показ уведомлений
function showToast(message, type = 'info') {
    alert(message);
}

// Остальные функции для работы с файлами...
function handleVideoSelect(input) {
    const file = input.files[0];
    if (!file) return;

    if (file.size > 128 * 1024 * 1024) {
        alert('Видео файл слишком большой. Максимальный размер: 128MB');
        input.value = '';
        return;
    }

    selectedFiles.video = file;
}

function handleAudioSelect(input) {
    const file = input.files[0];
    if (!file) return;

    if (file.size > 50 * 1024 * 1024) {
        alert('Аудио файл слишком большой. Максимальный размер: 50MB');
        input.value = '';
        return;
    }

    selectedFiles.audio = file;
}

function handleImageSelect(input) {
    const files = Array.from(input.files);

    for (let file of files) {
        if (file.size > 10 * 1024 * 1024) {
            alert(`Файл ${file.name} слишком большой. Максимальный размер: 10MB`);
            input.value = '';
            return;
        }
    }

    selectedFiles.images = files;
}

function validateForm() {
    const hasText = document.getElementById('textContent').value.trim().length > 0;
    const hasVideo = selectedFiles.video !== null;
    const hasAudio = selectedFiles.audio !== null;
    const hasImages = selectedFiles.images.length > 0;

    return hasText || hasVideo || hasAudio || hasImages;
}

function resetForm() {
    selectedFiles = {
        video: null,
        audio: null,
        images: [],
        text: ''
    };

    document.getElementById('partForm').reset();
    document.querySelectorAll('.preview-container').forEach(el => {
        el.style.display = 'none';
        el.innerHTML = '';
    });

    document.querySelectorAll('.upload-progress').forEach(el => {
        el.style.display = 'none';
    });
}

// Функция добавления комментариев
function addComment(button) {
    const partCard = button.closest('.part-card');
    const partTitle = partCard.querySelector('.part-title h3').textContent;
    alert(`Добавление комментария к партии: ${partTitle}`);
}