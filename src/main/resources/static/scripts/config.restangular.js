'use strict';

angular.module('app').config(function(RestangularProvider){
//	RestangularProvider.setBaseUrl('http://139.162.43.200:8080');
	
    RestangularProvider.setResponseExtractor(function(response, operation) {
    	var extractedData;
    	
    	if(operation == 'getList'){
    		for (var p1 in response) {
          		if ('_embedded' == p1) {
          			for (var p2 in response[p1]) {
          				if (response[p1].hasOwnProperty(p2)) {
      		      	      	extractedData = response[p1][p2];
      		      	    }
          			}
          		} else if ('page' == p1) {
          	      		extractedData.paging = response[p1];
          		} else if ('_links' == p1) {
      					extractedData.links = response[p1];
          		}
          	}
          	
          	if (!extractedData) {
          		extractedData = response;
          	}
    	} else {
    		extractedData = response;
    	}
      	
      	
      	return extractedData;
    });
})