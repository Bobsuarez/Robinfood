const { DynamoDB } = require('aws-sdk');
const AWS = require('aws-sdk');
const dynamodb = new AWS.DynamoDB.DocumentClient();
const OrdersPayloadRepository = 
require('../../../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');
const{mockResultDyanamo,mockResultDyanamoInit,mockConectionPayload}= require('../../../mock/dataevent');

const mockStoreId=16;
const mockConnectionId="RTAYTdcciYcCGNg=";

let mockDocumentClient = {
    query: {
      promise: jest.fn((params, callback) => callback('someData'))
    },
    put: {
        promise: jest.fn()
    },
    update: {
        promise: jest.fn()
    }
  };
  
  jest.mock("aws-sdk", () => {
    return {
      DynamoDB: {
        DocumentClient: jest.fn().mockImplementation(() => {
          return {
            query: () => mockDocumentClient.query,
              put: () => mockDocumentClient.put,
            update: () => mockDocumentClient.update
          };
        }),
      },
    };
  });


describe('Test with scanOrderPaylodsByStoreId', () => {
   
    test('where store exist', () => {
        mockDocumentClient.query.promise.mockReturnValue(Promise.resolve(mockResultDyanamo));
        return OrdersPayloadRepository.scanOrderPaylodsByStoreId(mockStoreId)
        .then(reason => {
            expect(reason).toEqual(mockResultDyanamo);
        });       
    });
});


describe('Test with scanOrderPaylodsByConectionId', () => {
   
    test('where conectionId exist', () => {
        mockDocumentClient.query.promise.mockReturnValue(Promise.resolve(mockResultDyanamo));
        return OrdersPayloadRepository.scanOrderPaylodsByConectionId(mockConnectionId)
        .then(reason => {
            expect(reason).toEqual(mockResultDyanamo);
        });       
    });   
    
    test('where conectionId exist but error in query', () => {

        mockDocumentClient.query.promise.mockReturnValue(Promise.reject(new Error("not found")));
        return OrdersPayloadRepository.scanOrderPaylodsByConectionId(mockConnectionId)
        .catch(reason => {
            expect(reason).toEqual(new Error("not found"));
        });       
    });
});

describe('Test with putOrderPayload', () => {
   
    test('where putOrderPayload exist', () => {
        mockDocumentClient.put.promise.mockReturnValue(Promise.resolve(mockResultDyanamoInit));
        return OrdersPayloadRepository.putOrderPayload(mockConectionPayload)
        .then(reason => {
            expect(reason).toEqual(mockResultDyanamoInit);
        });       
    });
});

describe('Test with updateOrderPayloadStatusByStoreId', () => {

  test('where updateOrderPayloadStatusByStoreId exist', () => {

      mockDocumentClient.update.promise.mockReturnValue(Promise.resolve(mockResultDyanamo));
      return OrdersPayloadRepository.updateOrderPayloadStatusByStoreId(mockStoreId)
      .then(reason => {
          expect(reason).toEqual(mockResultDyanamo);
      });       
  });
});

describe('Test with updateOrderPayloadOrdersByStoreId', () => {

  test('where updateOrderPayloadOrdersByStoreId exist', () => {

      mockDocumentClient.update.promise.mockReturnValue(Promise.resolve(mockResultDyanamo));
      return OrdersPayloadRepository.updateOrderPayloadOrdersByStoreId(mockStoreId, [])
      .then(reason => {
          expect(reason).toEqual(mockResultDyanamo);
      });       
  });
});

describe('Test with updateOrderPayloadConnectionIdByStoreId', () => {

  test('where updateOrderPayloadConnectionIdByStoreId exist', () => {

      mockDocumentClient.update.promise.mockReturnValue(Promise.resolve(mockResultDyanamo));
      return OrdersPayloadRepository.updateOrderPayloadConnectionIdByStoreId( mockStoreId, mockConnectionId)
      .then(reason => {
          expect(reason).toEqual(mockResultDyanamo);
      });       
  });
});
