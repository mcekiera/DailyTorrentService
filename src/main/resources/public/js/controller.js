Ractive.DEBUG = false;
var daily_torrent = function() {

	function submitCheck() {
		var check = inputEmail.get('valid') && inputRating.get('valid') && inputRadio.get('valid');
		formButton.set('enabled', check);
	}

	function clear() {
		$('input:radio').each(function () {
			$(this).prop('checked', false);
		});
		$('input:text').each(function () {
			$(this).value = "";
		})
	}

	function collectData() {
		var id = inputEmail.get('id');
		var rating = inputRating.get('rating');
		var whitelist = [];
		var blacklist = [];

		$('input:radio:checked').each(function () {
			if($(this).hasClass('whitelist')) {
				whitelist.push($(this).val());
			} else if($(this).hasClass('blacklist')) {
				blacklist.push($(this).val());
			}
			console.log($(this).className)
		});

		console.log({
			'id': id,
			'rating': rating,
			'whitelist': whitelist,
			'blacklist': blacklist
		});
	}

	function registerUser() {
		$.post('/api/profiles/',collectData(),function () {
			alert('success');
		})
	}

    var InputRactive = Ractive.extend({
        data: {
            valid: false
        },

        regex: /.*/,
        inputEl: null,
        verify: function () {
            this.set('valid', this.regex.test(this.inputEl.value));
			submitCheck();
        }
    });

    var inputEmail = new InputRactive({
        el: '#input-email-ractive',
        template: '#input-email-template',
        regex: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
		data: {
			id: ''
		}
    });

    var inputRating = new InputRactive({
        el: '#input-rating-ractive',
        template: '#input-rating-template',
        regex: /^(10|\d(\.\d)?)$/,
		data: {
			rating: '',
			valid: false
		}
    });

    var dbTable = new Ractive({
        el: '#db-table-ractive',
        template: '#db-table-template',
        data: {
            that: this,
            valid: false,
            movies: [],
            isLoaded: false,
            op: 0
        }

    });

    var inputRadio = new Ractive({
        el: '#input-radio-ractive',
        template: '#input-radio-template',
		verify: function () {
			if($('.whitelist:checked').length > 0) {
				this.set('valid', true);
			} else {
				this.set('valid', false)
			}
			submitCheck();
		},
        data: {
            lists: ["Whitelist","Blacklist"].sort(),
            genres: ['Drama', 'Comedy', 'Action', 'Romance', 'Thriller',
                'Animation', 'Family', 'Horror', 'Music', 'Adventure',
                'Fantasy', 'Sci-Fi', 'Mystery', 'Biography', 'Sport',
                'History', 'Musical', 'Western', 'War', 'Film-Noir'].sort(),
			valid: false
        }
    });

	var formButton = new Ractive({
		el: '#form-buttons-ractive',
		template: '#form-buttons-template',
		data: {
			active: false,
			enabled: false
		},

		clearRadio: clear,
		register: registerUser
	});

    inputRating.inputEl = inputRating.find("#input-rating-element");
    inputEmail.inputEl = inputEmail.find("#input-email-element");

    (function () {

        $.getJSON("/api/", function (data) {
            dbTable.set('movies', data);
			dbTable.set("isLoaded", true);
        });
    })();

    clear();
};

daily_torrent();


