export interface Transaction {
    transactionId: number,
    transactionType: number,
    transactionDate: Date,
    description: string,
    amount: number,
    accountFromId: number,
    accountToId: number
}