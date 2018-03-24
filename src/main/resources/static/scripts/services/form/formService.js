
angular.module('app').factory('formService',
		['$http', '$filter', '$rootScope',
		 function formService
		 ($http, $filter,$rootScope) {
    return { 
  	  genericGet : function(link, page, size){
	  	  return $http.get(link, {params:{'page':page, 'size':size}});
	  },
	  genericGetSort : function(link, param){
	  	  return $http.get(link, {params:param});
	  },
	  genericPatch : function(generic,link){
    	  return $http.patch(link,generic);
      },
      genericDel : function(id){
    	  return $http.delete(id);
      },
      genericPutUrl : function(object, link){
    	  return $http.put(link,object, {headers:{'Content-Type': 'text/uri-list'}});
      },
      genericPatchUrl : function(patient,link){
    	  return $http.patch(link,patient, {headers:{'Content-Type': 'text/uri-list'}});
      },
      saveMoneyControl : function(controlType, moneyControlList){
    	  return $http.post(_CONTEXT+'/service/saveMoneyControl?controlType='+controlType,moneyControlList);
      },
      saveCashBook : function(cashBookList){
    	  return $http.post(_CONTEXT+'/service/saveCashBook',cashBookList);
      },
      saveBalanceMaster : function(balanceMaster){
    	  return $http.post(_CONTEXT+'/service/saveBalanceMaster',balanceMaster);
      },
      loadBalanceMaster : function(masterId){
    	  return $http.get(_CONTEXT+'/service/getBalanceMaster?masterId='+masterId);
      },
      saveSchoolBudget : function(saveSchoolBudget){
    	  return $http.post(_CONTEXT+'/service/saveSchoolBudget',saveSchoolBudget);
      }
    };
}]);