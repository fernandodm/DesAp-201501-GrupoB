'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:agregarHistoriaCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('VerHistoriaCtrl', function ($scope,$http,$routeParams) {

    
	$http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/' + $routeParams.id).success(function (data) {
       //datos lo tenemos disponible en la vista gracias a $scope
       $scope.paciente = data;
       $scope.alergias = $scope.paciente.medicalHistory.allergies;

    });

    $scope.agregarAlergia = function() {
		$scope.alergias.push($scope.alergia);

		$http.put('http://localhost:8080/desapp-groupb-backend/rest/patients/update/' + $routeParams.id + '/' + $scope.alergia)
			.success(function () {
       
  

    	});

	};

	$scope.eliminarAlergia = function(alergia) {
		var index = $scope.alergias.indexOf(alergia);
		$scope.alergias.splice(index,1);
		$http.put('http://localhost:8080/desapp-groupb-backend/rest/patients/delete/' + $routeParams.id + '/' + alergia)
			.success(function () {
       
  
    	});

	};

	$scope.agregarDiagnostico = function() {
		location = '#/agregarDiagnostico/' + $routeParams.id;
	};

  });