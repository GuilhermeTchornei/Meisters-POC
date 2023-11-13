const mainContent = document.querySelector(".content");

onInit();

async function onInit() {
    mainContent.innerHTML = await getTasks();
}


async function addTask() {
    if (document.querySelector("form")) return;

    mainContent.innerHTML += newTaskForm();
    document.querySelector("form").addEventListener("submit", (event) => createTask(event));
    mainContent.scrollTop = mainContent.scrollHeight;
}