export interface TransactionInterface {
    accountFromId: number;
    accountToId: number;
    amount: number;
    description: String;
    transactionDate: Date;
    transactionId: number;
    transactionType: number;
}