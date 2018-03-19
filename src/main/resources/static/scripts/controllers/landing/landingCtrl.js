
'use strict';
angular
	.module('app')
		.controller('landingCtrl', [	'$scope', '$http', '$localStorage', 
									'$timeout', '$translate', 
									'$state' , '$stateParams', 'Restangular', 
									'toastr', '$rootScope', 'patientServices',
  function landingCtrl($scope, $http, $localStorage, 
		  			$timeout, $translate, 
		  			$state, $stateParams, Restangular, 
		  			toastr, $rootScope, patientServices) {
	
	$scope.$watch("init", function(){
	});
	
	$scope.patientProfile = {};
	$scope.patient = {};
	
	
	
	$scope.patchPatient = function(patient, link){
		patientServices.patchPatientParent(patient, link).then(function(response){
			toastr.success('saved');
		}).catch(function(response) {
			console.error('Error',response);
			toastr.error(response.data.message, 'Error');
	    });
	};
	
	
	$scope.selecTrack = function(){
		$state.go('app.track',{txn:$scope.txn})
	};
	
	$scope.selectMenu = function(menuCode){
		$state.go('app.txn',{menuCode: menuCode, txn:$scope.txn})
	};
	
	
  }
]);