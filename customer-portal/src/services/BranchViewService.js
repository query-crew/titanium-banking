import axios from "axios";

const BranchViewService = {

    branchDetails: function(branchId, onSuccess, onError) {
        axios.get(process.env.REACT_APP_BRANCH_API + branchId).then( (Response) => {
            onSuccess(Response.data);
        }).catch(err => {
            onError(err);
        });
    },

    searchBranches: function(pageNum, pageSize, onSuccess, onError) {
        axios.get(process.env.REACT_APP_BRANCH_API + "?page=" + pageNum + "&size=" + pageSize).then( (Response) => {
            const newBranchList = Response;
            onSuccess(newBranchList);
        }).catch(err => {
            onError(err);
        });
    },

    filterBranches: function(branches, searchCriteria) {
        let filteredBranches = branches;
        for(let branch in branches) {
            if(searchCriteria.state && branches[branch].state !== searchCriteria.state) {
                filteredBranches = filteredBranches.filter(br => br.state === searchCriteria.state);
            }
            if(searchCriteria.city && branches[branch].city !== searchCriteria.city) {
                filteredBranches = filteredBranches.filter(br => br.city === searchCriteria.city);
            }
            if(searchCriteria.branchName && branches[branch].branchName !== searchCriteria.branchName) {
                filteredBranches = filteredBranches.filter(br => br.branchName === searchCriteria.branchName);
            }
        }
        return filteredBranches;
    }
}

export default BranchViewService;