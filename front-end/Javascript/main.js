const mainContainer = document.querySelector(".content");

onInit();

async function onInit(){
    mainContainer.innerHTML = await getTasks();
}


function addTask(){
    console.log("teste");
}