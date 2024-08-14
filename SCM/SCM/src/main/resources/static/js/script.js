
function setTheme(theme){
    localStorage.setItem("theme",theme)
}

function getTheme(){
    var theme = localStorage.getItem("theme")
    console.log("theme---",theme);
    if(theme.length == 0)
        return "dark"
    return theme;
}

let currentTheme = getTheme();
document.addEventListener("DOMContentLoaded",()=>{
    changeTheme(currentTheme)
})


function changeTheme(currentTheme){
   //set to web page
    document.querySelector("html").classList.add(currentTheme);

    const changeThemeButton = document.getElementById("theme_changer");
    
    changeThemeButton.addEventListener("click",(event) =>{
        const oldTheme = currentTheme;
        if(currentTheme === "dark")
            currentTheme = "light"
        else
            currentTheme = "dark"

        setTheme(currentTheme);

        //removing old theme
        document.querySelector("html").classList.remove(oldTheme);

        //adding new theme
        document.querySelector("html").classList.add(currentTheme);

        //adding Dark or Light
        document.getElementById("theme_span").innerHTML = currentTheme === "dark" ? "Dark" : "Light"
    })


}



