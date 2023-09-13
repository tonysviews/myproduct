import EndpointInterface from "#{API_BASE_URL}/api/rest/endpoint/EndpointInterface.js";

// the request schema, this should be updated
// whenever changes to the endpoint parameters are made
// this is important because this is used to validate and parse the request parameters
const requestSchema = {
  "title" : "createProductRequest",
  "id" : "createProductRequest",
  "default" : "Schema definition for createProduct",
  "$schema" : "http://json-schema.org/draft-07/schema",
  "type" : "object",
  "properties" : {
    "product" : {
      "title" : "Product",
      "description" : "Product",
      "id" : "Product",
      "storages" : [ "SQL" ],
      "type" : "object",
      "properties" : {
        "proid" : {
          "title" : "Product.proid",
          "description" : "proid",
          "id" : "CE_Product_proid",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 255
        },
        "proname" : {
          "title" : "Product.proname",
          "description" : "proname",
          "id" : "CE_Product_proname",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 255
        },
        "proprice" : {
          "title" : "Product.proprice",
          "description" : "proprice",
          "id" : "CE_Product_proprice",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 255
        }
      }
    }
  }
}

// the response schema, this should be updated
// whenever changes to the endpoint parameters are made
// this is important because this could be used to parse the result
const responseSchema = {
  "title" : "createProductResponse",
  "id" : "createProductResponse",
  "default" : "Schema definition for createProduct",
  "$schema" : "http://json-schema.org/draft-07/schema",
  "type" : "object",
  "properties" : {
    "result" : {
      "title" : "result",
      "type" : "string",
      "minLength" : 1
    }
  }
}

// should contain offline mock data, make sure it adheres to the response schema
const mockResult = {};

class createProduct extends EndpointInterface {
	constructor() {
		// name and http method, these are inserted when code is generated
		super("createProduct", "POST");
		this.requestSchema = requestSchema;
		this.responseSchema = responseSchema;
		this.mockResult = mockResult;
	}

	getRequestSchema() {
		return this.requestSchema;
	}

	getResponseSchema() {
		return this.responseSchema;
	}
}

export default new createProduct();