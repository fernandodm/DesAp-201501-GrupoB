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
       		
       	//$http.put('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/update/' + $routeParams.idDiagnostico + '/' + $scope.nombre + '/' + $scope.sintomas)
    	//	.success(function (data) {

    		//location = '#/verHistoria/' + $routeParams.idPaciente;

   		//});
    }; 

    $scope.cancelar = function() {
  		
  		//$http.delete('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/delete/' + $routeParams.idDiagnostico).success(function (data) {
       		
       		

   		//});
  		//location = '#/verHistoria/' + $routeParams.idPaciente;
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
