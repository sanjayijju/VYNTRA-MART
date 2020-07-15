
//Common js code of all pages


///////////////////////////////////////////////////////////////////////////////////////////////////////////

//header.jsp js code starts
    //Search bar code starts

$(document).ready(function() {

    $('.search').each(function() {

        var self = $(this);
        var form = self.children('form');
        var input = form.children('input');
        var span = $('<span />').appendTo(form);
        var bar = self.children('.bar');
        var close = self.children('.close');
        var list = self.children('ul');

        input.keypress(function (e) {
            if(e.which && e.charCode) {
                resizeForText(input, span, $(this).val() + String.fromCharCode(e.keyCode | e.charCode));
            }
        });

        input.keyup(function(e) {
            if(e.keyCode === 8 || e.keyCode === 46) {
                resizeForText(input, span, $(this).val());
            }
        });

        resizeForText(input, span, self.val());

        form.submit(function(e) {
            e.preventDefault();
            if(!self.hasClass('prepare')) {
                input.blur();
                $('<span />').text(input.val()).appendTo(bar);
                self.addClass('prepare submit');

                setTimeout(function() {
                    self.removeClass('submit');
                }, 200);

                setTimeout(function() {
                    self.addClass('animate');
                    bar.animate({width: (self.outerWidth() - 32)}, 800, function() {
                        var searchW = ((list.outerWidth() + 32) > (72 + bar.outerWidth())) ? (list.outerWidth() + 32) : 72 + bar.outerWidth();
                        self.animate({width: searchW}, 400);
                        setTimeout(function() {
                            self.animate({height: self.outerHeight() + list.outerHeight()}, 500, function() {
                                list.addClass('show');
                            });
                        }, 200);
                    });

                    setTimeout(function() {
                        self.addClass('done');
                    }, 800);
                }, 1250);
            }
        });

        close.on('click', function(e) {
            self.removeClass('done');
            setTimeout(function() {
                input.val('');

                bar.animate({width: 32}, 1000, function() {
                    self.addClass('reset');
                    bar.children('span').remove();
                    setTimeout(function() {
                        self.removeClass('animate reset prepare');
                        setTimeout(function() {
                            input.animate({
                                width: 32
                            }, 400, function() {
                                bar.removeAttr('style');
                            });
                        }, 200);
                    }, 400);
                });

                list.removeClass('show');
                setTimeout(function() {
                    self.animate({height: 62}, 400, function() {
                        self.animate({width: 92}, 400, function() {
                            self.removeAttr('style');
                        });
                    });
                }, 200);
            }, 500);
        });

    });

});

function resizeForText(input, span, text) {
    text = (!text) ? ' ' : text;
    span.text(text);
    input.width(span.width());
}

    //Search bar code ends

$(document).ready(function(){
    $("#ProductBrandsParentDiv").hide();
    $("#ProductTypesParentDiv").hide();
    var types = ajaxUtils2('get_all_types','');
    ajaxUtils2('get_all_types','ProductTypes');
    ajaxUtils2('get_all_brands','ProductBrands');
});

function ajaxUtils2(url, box){

    $.ajax({
        type    : 'GET',
        url     : url,
        contentType : 'application/json',
        success : function(response){
            $.each(response, function(key, value){ //product_description/
                if(box == 'ProductTypes'){
                    var childNode = "<div class='col-3'><a class='alink' href='#'>"+value.typeName+"</a></div>";
                }
                else{
                    var childNode = "<div class='col-3'><a class='alink' href='#'>"+value.brandName+"</a></div>";
                }
                $("#"+box).append(childNode);
            });
        },
        error : function(response){
            console.log(response);
        }
    });
}

$(document).on('mouseover','#Types',function(){
    $("#ProductBrandsParentDiv").hide();
    $("#ProductTypesParentDiv").show();
});

$(document).on('mouseover','#Brands',function(){
    $("#ProductTypesParentDiv").hide();
    $("#ProductBrandsParentDiv").show();
});

$(document).on('mouseout','#Types',function(){
    $("#ProductTypesParentDiv").hide();
});

$(document).on('mouseout','#Brands',function(){
    $("#ProductBrandsParentDiv").hide();
});

//////
$(document).on('mouseover','#ProductTypesParentDiv',function(){
    $("#ProductBrandsParentDiv").hide();
    $("#ProductTypesParentDiv").show();
});

$(document).on('mouseover','#ProductBrandsParentDiv',function(){
    $("#ProductTypesParentDiv").hide();
    $("#ProductBrandsParentDiv").show();
});

$(document).on('mouseout','#ProductTypesParentDiv',function(){
    $("#ProductTypesParentDiv").hide();
});

$(document).on('mouseout','#ProductBrandsParentDiv',function(){
    $("#ProductBrandsParentDiv").hide();
});

//header.jsp js code ends

///////////////////////////////////////////////////////////////////////////////////////////////////////////

//home.jsp js code starts
$(document).ready(function(){
    advertisement();
});


function advertisement() {
    var randomColor1 = '#'+Math.floor(Math.random()*16777215).toString(16);
    var randomColor2 = '#'+Math.floor(Math.random()*16777215).toString(16);
    document.getElementById("Advertisements").style.backgroundImage = "linear-gradient(to right bottom, "+randomColor1+", "+randomColor2+")";
    setTimeout(function(){ advertisement() }, 3000);
}

//home.jsp js code ends

///////////////////////////////////////////////////////////////////////////////////////////////////////////

//product_descriptor.jsp js code starts

$(document).on('click','add_to_wishlist',function(){
    var wishlistId = -1; //change this
    var url = 'add_to_wishlist/${product.productId}/'+wishlistId;
    ajaxUtils2(url,'');
});


//product_descriptor.jsp js code ends