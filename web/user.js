


    var upload = function(files)
    {
        var dropzone = document.getElementById('dropzone');
        var username = document.getElementById('username').innerHTML;
        var userid = document.getElementById('userid').innerHTML;
        console.log(username);
        console.log(userid);

        for(var x=0 ; x<files.length ; x=x+1)
        {
            var formData = new FormData();
            console.log(username +'/'+files[x].name);
            console.log(userid);
            xhr = new XMLHttpRequest();
            formData.append('file', files[x]);
            //formData.append('path', files[x].name);
           // formData.append('id', userid);
            xhr.open('post','/mewpipe/rest/api/upload?path='+username+'/'+files[x].name+'&id='+userid);
            xhr.send(formData);
        }
}

dropzone.ondrop = function(e){
    e.preventDefault();
    this.className = "dropzone";
    upload(e.dataTransfer.files);
    }

dropzone.ondragover = function(){
    this.className = "dragover";
    return false;
    }

dropzone.ondragleave = function(){

    this.className = "dropzone";
    }