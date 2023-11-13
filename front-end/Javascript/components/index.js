function taskCard(task){
    let status = task.status === "PENDING";
    return `
        <div id=${task.id}  class="task-card">
            <div>
                <h1 class="${!status && "line-through"}">${task.title}</h1>
                <button onclick="patchStatus(${task.id})">
                    ${
                        status ? 
                        `<ion-icon name="radio-button-off-outline"></ion-icon>` : 
                        `<ion-icon name="radio-button-on-outline"></ion-icon>`
                    }
                </button>
            </div>
            <div>
                <p class="${!status && "line-through"}">${task.description || "<i>No description</i>"}</p>
                <button class="trash">
                    <ion-icon name="trash-outline"></ion-icon>
                </button>
            </div>
        </div>
    `
}