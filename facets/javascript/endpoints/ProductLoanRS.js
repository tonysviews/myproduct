import EndpointInterface from "#{API_BASE_URL}/api/rest/endpoint/EndpointInterface.js";

// the request schema, this should be updated
// whenever changes to the endpoint parameters are made
// this is important because this is used to validate and parse the request parameters
const requestSchema = {
  "title" : "ProductLoanRSRequest",
  "id" : "ProductLoanRSRequest",
  "default" : "Schema definition for ProductLoanRS",
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
    },
    "loanaccount" : {
      "title" : "LoanAccount",
      "description" : "LoanAccount",
      "id" : "LoanAccount",
      "storages" : [ "SQL" ],
      "type" : "object",
      "properties" : {
        "loanaccountname" : {
          "title" : "LoanAccount.loanaccountname",
          "description" : "loanaccountname",
          "id" : "CE_LoanAccount_loanaccountname",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 255
        },
        "loanid" : {
          "title" : "LoanAccount.loanid",
          "description" : "loanid",
          "id" : "CE_LoanAccount_loanid",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "string",
          "maxLength" : 255
        },
        "sanctionamount" : {
          "title" : "LoanAccount.sanctionamount",
          "description" : "sanctionamount",
          "id" : "CE_LoanAccount_sanctionamount",
          "storages" : [ "SQL" ],
          "nullable" : true,
          "readOnly" : false,
          "versionable" : false,
          "type" : "number"
        }
      }
    }
  }
}

// the response schema, this should be updated
// whenever changes to the endpoint parameters are made
// this is important because this could be used to parse the result
const responseSchema = {
  "title" : "ProductLoanRSResponse",
  "id" : "ProductLoanRSResponse",
  "default" : "Schema definition for ProductLoanRS",
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

class ProductLoanRS extends EndpointInterface {
	constructor() {
		// name and http method, these are inserted when code is generated
		super("ProductLoanRS", "POST");
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

export default new ProductLoanRS();