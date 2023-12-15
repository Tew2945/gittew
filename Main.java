// 65130500106 Teeratorn Thiengtham
import { createGuestList } from "./data/guestdata.js"
// import { GuestManagement } from './lib/GuestManagement.js';

// const createGuestList = require("./data/guestdata.js")

const guestList = createGuestList()
function guestForm() {
  //provide initial guests data list created from GuestManagement class
  let guests = guestList

  // 1. register event for searching and adding
  function registerEventHandling() {
    const searchGuestInput = document.getElementById("search-input")
    searchGuestInput.addEventListener("keyup", searchGuest)

    const addButton = document.getElementById("add-guest-btn")
    addButton.addEventListener("click", addGuest)
  }

  // 2. Function to display one guest in the display area
  function displayGuest(guestItem) {
    const divDisplayArea = document.getElementById("display-area")
    const divEle = document.createElement("div")
    const spanForName = document.createElement("span")
    const spanForIcon = document.createElement("span")

    spanForName.textContent = `${guestItem.firstname} ${guestItem.lastname}`
    spanForIcon.textContent = "[X]"
    spanForIcon.className = "remove-icon"
    spanForIcon.Id = `${guestItem.firstname}-${guestItem.lastname}`
    spanForIcon.style = "cursor:pointer;color:red"

    divEle.appendChild(spanForName)
    divEle.appendChild(spanForIcon)

    divDisplayArea.appendChild(divEle)

    spanForIcon.addEventListener("click", removeGuest)
  }

  // 3. Function to display all existing guests in the display area
  function displayGuests(guestResult) {
    const displayArea = document.getElementById("display-area")
    displayArea.innerHTML = ""
    guestResult.forEach((result) => {
      displayGuest(result)
    })
  }

  // 4. Function to search and display matching guests
  function searchGuest(event) {
    const result = guests.searchGuests(event.target.value)
    displayGuests(result)
  }

  // 5. Function to add a new guest
  function addGuest() {
    const firstnameInput = document.getElementById("firstname-input")
    const lastnameInput = document.getElementById("lastname-input")
    const firstname = firstnameInput.value.trim()
    const lastname = lastnameInput.value.trim()
    const result = guests.addNewGuest(firstname, lastname)

    // Display the newly added guest
    displayGuest(result[result.length - 1])

    firstnameInput.textContent = ""
    lastnameInput.textContent = ""
  }

  // 6. Function to remove a guest
  function removeGuest(event) {
    // const guestId = event.target.id;
    // const [firstname, lastname] = guestId.split('-');
    // const deleteGuest = { firstname, lastname };

    // guests.removeGuest(deleteGuest)
    // displayGuests(guests.getAllGuests());

    const name = event.target.getAttribute("id").split("-")
    guests.removeGuest({
      firstname: name[0],
      lastname: name[1],
    })
    event.target.parentElement.remove()
  }

  return {
    registerEventHandling,
    displayGuests,
    searchGuest,
    addGuest,
    removeGuest,
  }
}
// module.exports = guestForm
export { guestForm }
const { registerEventHandling, displayGuests } = guestForm()
registerEventHandling()
displayGuests(guestList.getAllGuests())
