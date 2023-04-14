import request from "@/utils/request";

export function addPokemen(pokemen){
    return request({
        url:'/pokemen/add',
        data:pokemen,
        method:'post',
    })
}

export function getPokemenList(pageNumber, pageSize, pokemen){
    return request({
        url:`/pokemen/list?pageNumber=${pageNumber}&pageSize=${pageSize}`,
        method:'post',
        data:pokemen,
    })
}

export function updatePokemens(pokemen){
    return request({
        url:'/pokemen/update',
        method:'post',
        data:pokemen,
    })
}
export function deletePokemen(id){
    return request({
        url:'/pokemen/delete?id='+id,
        method:'get',
    })
}