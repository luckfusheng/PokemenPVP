import request from "@/utils/request";

/*所有的属性 */
export function getAllAttribute(){
    return request({
        url:'/attributes/list',
        method:'get',
    })
}
/**克制关系 */
export function getRestrained(pid){
    return request({
        url:'/restrained/'+pid,
        method:'get',
    })
}
//对战结果的
export function getFightResult(lid,rid){
        return request({
            url:`/restrained/fight?lid=${lid}&rid=${rid}`,
            method:'get',
        })
}