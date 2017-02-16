var url = "localhost:8080/api/"

var ractive, movies;

var ractive = new Ractive({
    el: '#main',
    template: '#template',
    data: {
        movies: [],
        formatToQuery: function (title) {
            return title.replace(/\s+/g,'+');
        }
    }

});

var getMovies = function() {

    $.getJSON("/api/", function (data) {
        ractive.set('movies', data)
    });
} ;
getMovies()

$.post("/api/dis/",{ pid: 'cekin@vp.pl', mid: "tt4550098"});

