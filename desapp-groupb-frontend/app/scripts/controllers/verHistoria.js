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
  	    
	$http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/' + $routeParams.id).success(function (paciente) {
       //datos lo tenemos disponible en la vista gracias a $scope
       $scope.alergias = paciente.medicalHistory.allergies;
       $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/diagnoses/' + $routeParams.id).success(function (diagnosticos) {
       		$scope.diagnosticos = diagnosticos;
       	});
    });

    $scope.agregarAlergia = function() {

    	if($scope.alergias.indexOf($scope.alergia) == -1){
    		$scope.alergias.push($scope.alergia);
  		
			$http.put('http://localhost:8080/desapp-groupb-backend/rest/patients/update/' + $routeParams.id + '/' + $scope.alergia)
				.success(function () {
       
  
    		});
        $scope.alergia = "";
		}

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

  $scope.verDiagnostico = function(diagnostico) {
    
        location = '#/verDiagnostico/' + $routeParams.id + '/' + diagnostico.id;   
    
  };

	$scope.eliminarDiagnostico = function(diagnostico){

      var index = $scope.diagnosticos.indexOf(diagnostico);
      $scope.diagnosticos.splice(index,1);

        $http.post('http://localhost:8080/desapp-groupb-backend/rest/diagnoses/delete/' + $routeParams.id  + '/' + diagnostico.id).success(function (data){

        });

   }

  });