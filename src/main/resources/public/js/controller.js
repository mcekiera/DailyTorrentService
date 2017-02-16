var url = "localhost:8080/api/"

var ractive, movies;

var ractive = new Ractive({
    el: '#main',
    template: '#template',
    data: {
        movies: []
    }

});

var getMovies = function() {

    $.getJSON("/api/", function (data) {
        data.forEach(function (a) {
            console.log(a.title)
        });
        ractive.set('movies', data)
    });
} ;
getMovies()

