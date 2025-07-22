const btn = document.getElementById("desktopMenuToggle");
const nav2 = document.getElementById("nav2");

    btn.onclick = () =>{
        if(nav2.style.top == "0px")
            nav2.style.top = "60px";
        else
            nav2.style.top = "0px";
    }
  
