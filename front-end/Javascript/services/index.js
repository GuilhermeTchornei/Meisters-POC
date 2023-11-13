const tasks = new Map();
const noTasks = `
    <p>
        There are no tasks yet.
    </p>
    `;

async function getTasks() {
    try {
        const response = await fetch("http://localhost:8080/tasks");

        if (response.status !== 200) throw new Error("No Tasks!");

        const data = await response.json();

        if (data.length <= 0) throw new Error("No Tasks!");

        let tasksCards = "";
        data.map((task) => {
            tasks.set(task.id, task);
            tasksCards += taskCard(task);
        });
        return tasksCards;
    }
    catch (err) {
        console.error(err);
        return noTasks;
    }
}

async function patchStatus(taskId) {
    const task = tasks.get(taskId);
    if (task.status === "PENDING") task.status = "COMPLETED";
    else task.status = "PENDING";

    try {
        const response = await fetch(`http://localhost:8080/tasks/${taskId}`, {
            method: "PATCH",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ status: task.status })
        });

        if (response.status !== 200) throw new Error(response.status);

        tasks.set(taskId, task);
        const taskElement = document.getElementById(task.id);
        taskElement.outerHTML = taskCard(task);
    }
    catch (err) {
        console.error(err);
    }
}

async function deleteTask(taskId) {
    try {
        const response = await fetch(`http://localhost:8080/tasks/${taskId}`, { method: "DELETE" });

        if (response.status !== 202) throw new Error(response.status);

        tasks.delete(taskId);
        const taskElement = document.getElementById(taskId);
        taskElement.remove();

        if (tasks.size === 0) {
            document.querySelector(".content").innerHTML = `
            <p>
                There are no tasks yet.
            </p>
            `;
        }
    }
    catch (err) {
        console.error(err);
    }
}

async function createTask(event) {
    event.preventDefault();
    const task = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        status: "PENDING"
    };

    try {
        const response = await fetch("http://localhost:8080/tasks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
        });

        if (response.status !== 201) throw new Error(response.status);

        const data = await response.json();

        tasks.set(data.id, data);
        const mainContent = document.querySelector(".content");
        if (tasks.size === 1)
            mainContent.innerHTML = taskCard(data);
        else mainContent.innerHTML += taskCard(data);
    }
    catch (err) {
        console.error(err);
    }

    document.querySelector("form").outerHTML = "";
}