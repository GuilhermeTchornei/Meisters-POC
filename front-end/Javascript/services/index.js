const tasks = new Map();

async function getTasks() {
    return await fetch("http://localhost:8080/tasks")
    .then(res => {
        if(res.status === 200)
            return res.json();
        else
            throw new Error("No Tasks!");
    })
    .then(json => {
        if(json.length <= 0) throw new Error("No Tasks!");
        let tasksCards = "";
        json.map((task, index) => {
            tasks.set(task.id, task);
            tasksCards += taskCard(task);
            if(index < json.length - 1) tasksCards += `<div class="divider"></div>`
        });
        return tasksCards;
    })
    .catch(() => `
    <p>
        There are no tasks yet.
    </p>
    `);
}