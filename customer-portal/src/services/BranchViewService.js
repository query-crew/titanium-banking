import axios from "axios";

const BranchViewService = {

    branchDetails: function(branchId, onSuccess, onError) {
        axios.get("http://localhost:8080/branch/" + branchId).then( (Response) => {
            console.log(Response.data);
            onSuccess(Response.data.branch);
        }).catch(err => {
            onError(err);
        });
    },

    searchBranches: function(onSuccess, onError) {
        axios.get("http://localhost:8080/branch").then( (Response) => {
            const newBranchList = Response.data.branches;
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