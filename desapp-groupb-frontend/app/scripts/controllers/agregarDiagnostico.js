'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:AgregarDiagnosticoCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
 var app =  angular.module('myappApp');

 app.controller('AgregarDiagnosticoCtrl', function ($http,$scope,$routeParams) {
 	
 	$scope.tags=[];
	 

 	$scope.agregarSintoma = function() {
 		$scope.sintomas = [];
 		var i;
 		for (i = 0; i < $scope.tags.length; i++) { 
    		$scope.sintomas.push($scope.tags[i].text);
		}

		$http.get('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/' + $scope.sintomas).success(function (data) {
       		
       		$scope.diagnosticos = data;

   		});

    };

 
    $scope.crearDiagnostico = function() {    	
       	
   		$http({
            method: 'POST',
            url: 'http://localhost:8080/desapp-groupb-backend/rest/diagnoses/create/',
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function(obj) {
                var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            return str.join("&");
            },

            data: {id: $routeParams.id, name: $scope.nombre, symptoms: $scope.sintomas, date: $scope.fecha}


        }).success(function (data) {
                    location = '#/darTratamiento/' + data.id;
                });
        
        changeClass();
      

    }; 

    $scope.cancelar = function() {
  		
  		location = '#/verHistoria/' + $routeParams.id;
    };   

  });


 app.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress ", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });
 
                event.preventDefault();
            }
        });
    };
});
