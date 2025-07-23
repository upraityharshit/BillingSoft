const btn = document.getElementById("desktopMenuToggle");
const nav2 = document.getElementById("nav2");

    btn.onclick = () =>{
        if(nav2.style.top == "0px")
            nav2.style.top = "50px";
        else
            nav2.style.top = "0px";
    }

function toggleMenu() {
    const menu = document.getElementById('profile-menu');
    menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
}

// Close the menu if clicked outside
document.addEventListener('click', function (event) {
    const menu = document.getElementById('profile-menu');
    const icon = document.querySelector('.circle-icon');
    if (!icon.contains(event.target) && !menu.contains(event.target)) {
        menu.style.display = 'none';
    }
});
  
