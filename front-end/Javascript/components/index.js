function taskCard(task){
    let status = task.status === "PENDING";
    return `
        <div id=${task.id}  class="task-card">
            <div>
                <h1 class="${!status && "line-through"}">${task.title}</h1>
                <button type="button" onclick="patchStatus(${task.id})">
                    ${
                        status ? 
                        `<ion-icon name="radio-button-off-outline"></ion-icon>` : 
                        `<ion-icon name="radio-button-on-outline"></ion-icon>`
                    }
                </button>
            </div>
            <div>
                <p class="${!status && "line-through"}">${task.description || "<i>No description</i>"}</p>
                <button class="trash" type="button" onclick="deleteTask(${task.id})">
                    <ion-icon name="trash-outline"></ion-icon>
                </button>
            </div>
        </div>
    `
}

function newTaskForm(){
    return `
        <form>
            <input id="title" type="text" placeholder="Title..." maxlength="50" onchange="" required />
            <textarea id="description" placeholder="Description..." maxlength="255"></textarea>
            <button class="submit" type="submit">Submit</button>
            <button class="cancel" type="button" onclick="closeForm()">Cancel</button>
        </form>
        `;
}

function closeForm(){
    document.querySelector("form").outerHTML = "";
}