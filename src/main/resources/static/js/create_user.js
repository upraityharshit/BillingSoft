const createText = document.querySelector(".title-text .create");
const createForm = document.querySelector("form.create");
const createBtn = document.querySelector("label.create");
const editBtn = document.querySelector("label.edit");
const editLink = document.querySelector("form .edit-link a");

editBtn.onclick = () => {
  createForm.style.marginLeft = "-50%";
  createText.style.marginLeft = "-50%";
};
createBtn.onclick = () => {
  createForm.style.marginLeft = "0%";
  createText.style.marginLeft = "0%";
};
editLink.onclick = () => {
  editBtn.click();
  return false;
};
