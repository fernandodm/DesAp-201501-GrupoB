'use strict';

/**
 * @ngdoc function
 * @name myappApp.controller:agregarHistoriaCtrl
 * @description
 * # AboutCtrl
 * Controller of the myappApp
 */
angular.module('myappApp')
  .controller('VerHistoriaCtrl', VerHistoriaCtrl);


  function VerHistoriaCtrl($http, $routeParams, $resource, DTOptionsBuilder, DTColumnDefBuilder) {
    
    var vm = this;
     
    $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/list/').success(function (data) {
       vm.pacientes = data;
    });

    vm.dtOptions = DTOptionsBuilder.newOptions()
                            .withOption('info',false)
                            .withDisplayLength(10)
                            .withBootstrap();
    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1).notSortable()
    ];

    $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/' + $routeParams.id).success(function (paciente) {
       vm.alergias = paciente.medicalHistory.allergies;
       $http.get('http://localhost:8080/desapp-groupb-backend/rest/patients/diagnoses/' + $routeParams.id).success(function (diagnosticos) {
          vm.diagnosticos = diagnosticos;
        });
    });

    vm.agregarAlergia = function() {

      if(vm.alergias.indexOf(vm.alergia) == -1){
        vm.alergias.push(vm.alergia);
      
      $http.put('http://localhost:8080/desapp-groupb-backend/rest/patients/update/' + $routeParams.id + '/' + vm.alergia)
        .success(function () {
       
  
        });
        vm.alergia = "";
    }

  };

  vm.eliminarAlergia = function(alergia) {
    var index = vm.alergias.indexOf(alergia);
    vm.alergias.splice(index,1);
    $http.put('http://localhost:8080/desapp-groupb-backend/rest/patients/delete/' + $routeParams.id + '/' + alergia)
      .success(function () {
       
  
      });

  };

  vm.agregarDiagnostico = function() {
    
        location = '#/agregarDiagnostico/' + $routeParams.id;   
    
  };

  vm.verDiagnostico = function(diagnostico) {
    
        location = '#/verDiagnostico/' + $routeParams.id + '/' + diagnostico.id;   
    
  };
}

  /*function ($scope,$http,$routeParams) {
  	    
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

});*/