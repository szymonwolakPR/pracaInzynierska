$(document).ready(function () {
	   var image = $('img');
	   var xref = {
	   		bujne: "<h1> Bujne </h1> ",
	   		roztoka_brzeziny: "<h1> Roztoka - Brzeziny </h1> ",
	   		podole_gorowa: "<h1> Podole - Górowa </h1> ",
	   		przydonica: "<h1> Przydonica </h1> ",
	   		przydonica_glinik: "<h1> Przydonica - Glinik </h1> ",
	   		jelna: "<h1> Jelna </h1> ",
	   		jelna_dzialy: "<h1> Jelna - Działy </h1> ",
	   		tropie: "<h1> Tropie </h1> ",
	   		roznow: "<h1> Rożnów </h1> ",
	   		bartkowa_posadowa: "<h1> Bartkowa - Posadowa </h1> ",
	   		grodek: "<h1> Gródek nad Dunajcem </h1> ",
	   		sienna: "<h1> Sienna </h1> ",
	   		lipie: "<h1> Lipie </h1> ",
	   		zbyszyce: "<h1> Zbyszyce </h1> "
	   };
	   var defaultDipTooltip = 'I know you want the dip. But it\'s loaded with saturated fat, just skip it and enjoy as many delicious, crisp vegetables as you can eat.';

	   image.mapster(
       {
       		fillOpacity: 0.9,
       		fillColor: "FFFFFF",
       		strokeColor: "2a7064",
       		strokeOpacity: 0.8,
       		strokeWidth: 3,
       		stroke: true,
            isSelectable: true,
			singleSelect: true,
            mapKey: 'name',
            listKey: 'name',
            onMouseover: function (e) {
                var newToolTip = defaultDipTooltip;
                $('#selections').html(xref[e.key]);
                image.mapster('set_options',{areas: [{
                	toolTip: newToolTip
                	}]
                });
            },
            onClick: function() {
               window.location=this.href;
               return false;
            },
            toolTipClose: ["tooltip-click", "area-click"],
            areas: [
                {
                	key: "bujne",
					fadeDuration: 1500
                },
				{
                	key: "roztoka_brzeziny",
					fillColor: 'ff0000',
					fadeDuration: 1500
                },
				{
                	key: "podole_gorowa"
                },
				{
                	key: "przydonica"
                },
				{
                	key: "przydonica_glinik"
                },
				{
                	key: "jelna_dzialy"
                },
				{
                	key: "tropie"
                },
                {
                	key: "roznow"
                },
				{
                	key: "bartkowa_posadowa",
                	fillColor: "FFFFFF"
                },
				{
                	key: "grodek",
                	fillColor: "FFFFFF"
                },
				{
                	key: "sienna",
                	fillColor: "FFFFFF"
                },
				{
                	key: "lipie",
                	fillColor: "FFFFFF"
                },
				{
                	key: "zbyszyce",
                	fillColor: "FFFFFF"
                },
				{
                	key: "jelna",
                	fillColor: "FFFFFF"
                },
                ]
        });
      });
