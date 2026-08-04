const API = "http://localhost:8080/notes";

const title = document.getElementById("title");
const content = document.getElementById("content");

const noteId = document.getElementById("noteId");

const saveBtn = document.getElementById("saveBtn");
const cancelBtn = document.getElementById("cancelBtn");

const notesContainer = document.getElementById("notesContainer");

window.onload = loadNotes;

saveBtn.addEventListener("click", saveNote);

cancelBtn.addEventListener("click", resetForm);

async function loadNotes(){

    const response = await fetch(API);

    const notes = await response.json();

    notesContainer.innerHTML = "";

    notes.forEach(note=>{

        notesContainer.innerHTML += `

        <div class="note">

            <h3>${note.title}</h3>

            <p>${note.content}</p>

            <div class="actions">

                <button
                    class="edit"
                    onclick="editNote(${note.id},
                    '${escapeString(note.title)}',
                    '${escapeString(note.content)}')">

                    Edit

                </button>

                <button
                    class="delete"
                    onclick="deleteNote(${note.id})">

                    Delete

                </button>

            </div>

        </div>

        `;

    });

}

async function saveNote(){

    const data={

        title:title.value,
        content:content.value

    };

    if(noteId.value===""){

        await fetch(API,{

            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify(data)

        });

    }

    else{

        await fetch(`${API}/${noteId.value}`,{

            method:"PUT",

            headers:{
                "Content-Type":"application/json"
            },

            body:JSON.stringify(data)

        });

    }

    resetForm();

    loadNotes();

}

function editNote(id,noteTitle,noteContent){

    noteId.value=id;

    title.value=noteTitle;

    content.value=noteContent;

    saveBtn.innerText="Update Note";

    cancelBtn.style.display="inline-block";

}

async function deleteNote(id){

    if(!confirm("Delete this note?"))
        return;

    await fetch(`${API}/${id}`,{

        method:"DELETE"

    });

    loadNotes();

}

function resetForm(){

    noteId.value="";

    title.value="";

    content.value="";

    saveBtn.innerText="Add Note";

    cancelBtn.style.display="none";

}

function escapeString(str){

    return str
        .replace(/'/g,"\\'")
        .replace(/"/g,"&quot;");

}