jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    console.log("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    //this.value = "";
                    $("#" + opts.Img).attr('src', '');
                    return false
                }
                var uAgent = navigator.userAgent.toLowerCase();
                if (uAgent.indexOf("msie") > -1 || uAgent.indexOf("rv:11") > -1) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
                    }
                } else {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                }
                opts.Callback()
            }else{
            	//$("#" + opts.Img).attr('src', '');
            	opts.Callback()
            }
        })
    },
    uploadPreviewNoImg: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    console.log("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    console.log("1:"+opts.obj);
                    //$("#" + opts.Img).attr('src', '');
                    return false
                }
                var uAgent = navigator.userAgent.toLowerCase();
                if (uAgent.indexOf("msie") > -1 || uAgent.indexOf("rv:11") > -1) {
                    try {
                        //$("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                        console.log("2:"+opts.obj);
                    } catch (e) {
                        
                    }
                } else {
                	var fileSize = this.files[0].size / 1024;
                	console.log("3:"+fileSize);
                	if(fileSize < 200){
                		$(opts.obj).attr('src', _self.getObjectURL(this.files[0]));
                		$(opts.obj).attr('style', "display: block;");
                		$($(opts.obj).parent().children()[1]).attr('style', "display: block;position: relative;top: -210px;left: 100px;");
                	}else{
                		$(opts.obj).attr('src', '');
                		$(opts.obj).attr('value', "");
                		$(opts.obj).attr('style', "display: none;");
                		$($(opts.obj).parent().children()[1]).attr('style', "display: none;");
                		$($(opts.obj).parent().children()[1]).attr('value', "");
                		var input_files = $($($(opts.obj).parent().parent().children()[3]).find("input")[0]);
                		
                		input_files.after(input_files.clone().val("")); 
                		input_files.remove(); 
                		
                		return false
                	}
                }
                opts.Callback()
            }else{
            	opts.Callback()
            }
        })
    }
});


var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
function fileChange(target) {
	var fileSize = 0; 
	var filetypes =[".jpg",".png"];//".rar",".txt",".zip",".doc",".ppt",".xls",".pdf",".docx",".xlsx" 
	var filepath = target.val();
	
	
	var filemaxsize = 1024*2;//2M
	if(filepath){
		var isnext = false; 
		var fileend = filepath.substring(filepath.lastIndexOf("."),filepath.length);
		if(filetypes && filetypes.length>0){
			for(var i =0; i<filetypes.length;i++){
				if(filetypes[i]==fileend){
					isnext = true; 
					break;
				}
			} 
		} 
		if(!isnext){ 
			return "请选择JPG或PNG类型文件"; 
		} 
	}else{
		return ""; 
	}
	
	if (isIE && !target.prop('files')) {
		var filePath = target.val(); 
		var fileSystem = new ActiveXObject("Scripting.FileSystemObject"); 
			if(!fileSystem.FileExists(filePath)){
			return "附件不存在，请重新输入！"; 
		} 
		var file = fileSystem.GetFile (filePath); 
		fileSize = file.Size; 
	} else {
		fileSize = target.prop('files')[0].size; 
	}

	var size = fileSize / 1024; 
	if(size>filemaxsize){
		return "附件大小不能大于"+filemaxsize/1024+"M！"; 
	}
	
	if(size<=0){
		return "附件大小不能为0M！"; 
	}
}

function autoAddEllipsis(pStr, pLen) {
    // 原字符串长度
    var _strLen = pStr.length;
    var _tmpCode;
    var _cutString;
    // 默认情况下，返回的字符串是原字符串的一部分
    var _cutFlag = "1";
    var _lenCount = 0;
    var _ret = false;
    if (_strLen <= pLen / 2) {
        _cutString = pStr;
        _ret = true;
    }
    if (!_ret) {
        for (var i = 0; i < _strLen; i++) {
            if (isFull(pStr.charAt(i))) {
                _lenCount += 2;
            } else {
                _lenCount += 1;
            }

            if (_lenCount > pLen) {
                _cutString = pStr.substring(0, i);
                _ret = true;
                break;
            } else if (_lenCount == pLen) {
                _cutString = pStr.substring(0, i + 1);
                _ret = true;
                break;
            }
        }
    }
    if (!_ret) {
        _cutString = pStr;
        _ret = true;
    }
    if (_cutString.length == _strLen) {
        _cutFlag = "0";
    }
    return {"cutstring": _cutString, "cutflag": _cutFlag};
}

function isFull(pChar) {
    for (var i = 0; i < pChar.strLen; i++) {
        if ((pChar.charCodeAt(i) > 128)) {
            return true;
        } else {
            return false;
        }
    }
}

Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}

//处理特殊字符,包括单引号,双引号
function htmlDecodeByRegExp(str){
    var s = "";
    if(str==null || str.length == 0 ) {
        return "";
    }
    //双引号
    s = str.replace(/"/g,"&quot;");
    //单引号
    s = s.replace(/'/g,"&lsquo;");
    return s;
}