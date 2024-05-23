const AWS = require('aws-sdk');

const OrdersPayloadRepository = 
require('../../../../infrastructure/persistence/repositories/dynamo/OrdersPayloadRepository');

const mockConnectionId="RTAYTdcciYcCGNg=";

jest.mock('aws-sdk', () => {
  const mDocumentClient = { query:jest.fn() };
  const mDynamoDB = { DocumentClient: jest.fn(() => mDocumentClient) };
  return { DynamoDB: mDynamoDB };
});
const mDynamoDb = new AWS.DynamoDB.DocumentClient();

describe('Test with scanOrderPaylodsByStoreId', () => {

  afterAll(() => {
    jest.resetAllMocks();
  });

  test('should get scanOrderPaylodsByStoreId', async () => {
    const mResult = { name: 'test teng' };
    mDynamoDb.query.mockImplementationOnce((_, callback) => callback(null, mResult));
     await expect(OrdersPayloadRepository.scanOrderPaylodsByConectionId(mockConnectionId)).
     rejects.toThrowError(undefined); 
  });

  test('where scanOrderPaylodsByStoreId Exception', async () => {
    mDynamoDb.query.mockImplementationOnce((_, callback) => callback(new Error("network")));

      await expect(OrdersPayloadRepository.scanOrderPaylodsByConectionId(mockConnectionId))
      .rejects.toThrowError(undefined); 
  });
});



