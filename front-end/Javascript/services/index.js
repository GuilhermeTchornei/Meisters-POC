const tasks = new Map();

async function getTasks() {
    return await fetch("http://localhost:8080/tasks")
        .then(res => {
            if (res.status !== 200) throw new Error("No Tasks!");

            return res.json();
        })
        .then(json => {
            if (json.length <= 0) throw new Error("No Tasks!");
            let tasksCards = "";
            json.map((task, index) => {
                tasks.set(task.id, task);
                tasksCards += taskCard(task);
                if (index < json.length - 1) tasksCards += `<div class="divider"></div>`
            });
            return tasksCards;
        })
        .catch(() => `
    <p>
        There are no tasks yet.
    </p>
    `);
}

function patchStatus(taskId) {
    const task = tasks.get(taskId);
    if (task.status === "PENDING") task.status = "COMPLETED";
    else task.status = "PENDING";

    fetch(`http://localhost:8080/tasks/${taskId}`, {
        method: "PATCH",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ status: task.status })
    })
        .then(res => {
            if (res.status !== 200) throw new Error(res.status);

            tasks.set(taskId, task);
            const taskElement = document.getElementById(task.id);
            taskElement.outerHTML = taskCard(task);
        })
        .catch(err => {
            console.error(err);
        });
}
