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
            json.map((task) => {
                tasks.set(task.id, task);
                tasksCards += taskCard(task);
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

function deleteTask(taskId){
    fetch(`http://localhost:8080/tasks/${taskId}`,{method:"DELETE"})
    .then(res => {
        if(res.status !== 202) throw new Error(res.status);

        tasks.delete(taskId);
        const taskElement = document.getElementById(taskId);
        taskElement.remove();
    })
    .catch(err => {
        console.error(err);
    });
}

function createTask(event){
    event.preventDefault();
    const task = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        status: "PENDING"
    };

    fetch("http://localhost:8080/tasks",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(task)
        }).then(res=>{
        if(res.status!==201)throw new Error(res.status);
        res.json().then(data=>{
            tasks.set(data.id, data);
            const mainContent = document.querySelector(".content");
            mainContent.innerHTML += taskCard(data);
        })
    })
    .catch(err=>console.error(err));

    document.querySelector("form").outerHTML = "";
}