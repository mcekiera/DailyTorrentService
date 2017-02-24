var daily_torrent = function() {


    var arr = [];

    var dbTable = new Ractive({
        el: '#db-table-ractive',
        template: '#db-table-template',
        data: {
            that: this,
            valid: false,
            movies: arr,
            isLoaded: false,
            formatToQuery: function (title) {
                return title.replace(/\s+/g, '+');
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
        inputEl: this.find("#input-email-element"),
        verify: function () {
            this.set('valid', this.regex.test(this.inputEl.value));
        }
    });

    var inputRating = new Ractive({
        el: '#input-rating-ractive',
        template: '#input-rating-template',
        data: {
            valid: false
        },

        regex: /10|\d(\.\d)?/,
        inputEl: null,
        verify: function () {
            this.set('valid', this.regex.test(this.inputEl.value));
        }
    });

    var inputRadio = new Ractive({
        el: '#input-radio-ractive',
        template: '#input-radio-template',
        data: {
            lists: ["Whitelist","Blacklist"].sort(),
            genres: ['Drama', 'Comedy', 'Action', 'Romance', 'Thriller',
                'Animation', 'Family', 'Horror', 'Music', 'Adventure',
                'Fantasy', 'Sci-Fi', 'Mystery', 'Biography', 'Sport',
                'History', 'Musical', 'Western', 'War', 'Film-Noir'].sort()
        },

        clearRadio: clear,
        register: function () {
            
        }
    });

    inputRating.inputEl = inputRating.find("#input-rating-element");
    inputEmail.inputEl = inputEmail.find("#input-email-element");

    function clear() {
        $('input:radio').each(function () {
            $(this).prop('checked', false);
        });
        $('input:text').each(function () {
            $(this).value = "";
        })
    };

    var getMovies = function () {

        $.getJSON("/api/", function (data) {
            dbTable.set('movies', data)
        });
    };

    setTimeout(function () {
        dbTable.set("isLoaded", true);
        dbTable.animate("op", 1, {
            easing: 'easeOut',
            duration: 3000
        });
    }, 5000)


    getMovies();
    clear();
};

daily_torrent();


