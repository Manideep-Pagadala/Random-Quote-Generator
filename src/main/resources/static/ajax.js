$(document).ready(function() {
	$("#countryFeild").change(function() {
		loadStates();
	});

	$("#stateField").change(function() {
		loadCities();
	});

	function loadStates() {
		var selectedCountry = $("#countryFeild").val();

		$("#stateField, #cityField").empty();

		$.get({
			url: "/states",
			data: { cid: selectedCountry },
			success: function(data) {
				$.each(data, function(key, value) {
					$("#stateField").append($('<option>', {
						value: key,
						text: value
					}));
				});
				$("#stateField").trigger("change");
			},
		});
	}

	function loadCities() {
		var selectedState = $("#stateField").val();
		$("#cityField").empty();
		$.get({
			url: "/cities",
			data: { sid: selectedState },
			success: function(data) {
				$.each(data, function(key, value) {
					$("#cityField").append($('<option>', {
						value: key,
						text: value
					}));
				});
			},
		});
	}
});