var url = "localhost:8080/api/"


var arr = [];

var table = new Ractive({
    el: '#main',
    template: '#dbTable',
    onInput: function () {
        console.log(/^\d+$/.test(document.getElementById("emailInput2").value));
        this.set('valid',/^\d+$/.test(document.getElementById("emailInput2").value));
    },
    data: {
        that: this,
        valid: false,
        movies: arr,
        isLoaded: false,
        formatToQuery: function (title) {
            return title.replace(/\s+/g,'+');
        },
        op: 0
    }

});

var inputEmail = new Ractive({
    el: '#input-email-ractive',
    template: '#input-email-template',
    data: {
        valid: false
    },

    regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
    inputEl: null,
    verify: function(){
        this.set('valid',this.regex.test(this.inputEl.value));
    }
});

inputEmail.inputEl = document.getElementById('input-email-element');

var inputRating = new Ractive({
    el: '#input-rating-ractive',
    template: '#input-rating-template',
    data: {
        valid: false
    },

    regex: /10|\d(\.\d)?/,
    inputEl: null,
    verify: function(){
        this.set('valid',this.regex.test(this.inputEl.value));
    }
});

inputEmail.inputEl = document.getElementById('input-email-element');
inputRating.inputEl = document.getElementById('input-rating-element');




var getMovies = function() {

    $.getJSON("/api/", function (data) {
        table.set('movies', data)
    });
} ;

setTimeout(function () {
    table.set("isLoaded", true);
    table.animate("op",1,{
        easing: 'easeOut',
        duration: 3000
    });
},5000)


getMovies();


