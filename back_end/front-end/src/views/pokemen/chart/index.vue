<template>
    <div>
        <el-container class="box-card">
            <el-row>
                <el-col :span="24" >
                    <el-card class="vs" >
                        <div style="display:flex;justify-content:space-between;">
                            <div >
                                <!-- 动态添加比赛结果样式 -->
                                <el-avatar :class="{victoryr:lflag,defeated:rflag}" 
                                    style="width: 70px; height: 70px;"
                                    @click.native="clear" 
                                    :src="!fightPokemen[0] ? require('../../../assets/xwen.png'):
                                    upload_url+fightPokemen[0].url" >
                                </el-avatar>
                                <div class="hg" v-if="lflag"></div>
                            </div>
                            
                            <el-image style="width: 70px; height: 70px;"  @click.native="vsflag && fight()"
                                :src="require('../../../assets/vs.png')" >
                            </el-image>
                            <div >
                                <el-avatar :class="{victoryl:rflag,defeated:lflag}" 
                                    style="width: 70px; height: 70px;" 
                                    @click.native="clear" 
                                    :src="!fightPokemen[1] ? require('../../../assets/xwen.png'):
                                    upload_url+fightPokemen[1].url" >
                                </el-avatar>
                                <div class="hg" v-if="rflag"></div>
                            </div>
                        </div>
                        
                    </el-card>
                </el-col>
            </el-row>
            <el-container style="display: flex;justify-content: center;">
                <el-row style="width:60vw;">
                    <el-col :span="6" v-for="pokemen in pokemenList" :key="pokemen.id" >
                        <el-card @click.native="addFight(pokemen)" style="margin:10px;" >
                            <img :src="upload_url+pokemen.url"  style="width: 150px; height: 150px;">
                            <div style="padding: 14px;">
                                <span>编号：{{pokemen.number}}</span><br>
                                <span>姓名：{{pokemen.name}}</span><br>
                                <br>
                                <span>{{pokemen.characteristic}}</span><br>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
                
            </el-container>
            
        </el-container>
    </div>
</template>

<script>

    import {getPokemenList, } from '@/api/manage';
    import { getAllAttribute,getFightResult } from '@/api/attribute';
    export default {
        data() {
            return {
                lflag:false,
                rflag:false,
                vsflag:true,
                fightPokemen:[],
                pokemenFormDialogVisible:false,
                queryForm:{
                    name:'',
                    id:'',
                },
                pokemenForm:{
                    id:'',
                    number:'',
                    name:'',
                    ss:'',
                    url:'',
                    stature:'',
                    weight:'',
                    characteristic:'',
                    attributes:'',
                },
                attributesList:[],
                attributesListChecked:[],
                pokemenList:[],
                pageNumber:1,
                pageSize:5,
                
            }
        },
        methods: {
            //点击对战时触发
            async fight(){
                if(this.fightPokemen.length<2){
                    this.$message.error("缺少选手!")
                    return
                }
                this.vsflag = false // 是否打过的标志位
                let fightMessage = ""
                await getFightResult(this.fightPokemen[0].id,this.fightPokemen[1].id).then(resp=>{
                    if(resp.code == 200){
                        const data = resp.data
                        if(data.lplayer!=[]){
                            data.lplayer.forEach(item=>{
                                fightMessage+=`${this.fightPokemen[0].name}的${item.advantageName}
                                属性克制了对手的${item.inferiorName}属性，增加了${item.offset}攻击力！！<br>`
                            })
                        }
                        if(data.rplayer!=[]){
                            data.rplayer.forEach(item=>{
                                fightMessage+=`${this.fightPokemen[1].name}的${item.advantageName}
                                属性克制了对手的${item.inferiorName}属性，增加了${item.offset}攻击力！！<br>`
                            })
                        }
                        if(data.lplayer==[] && !data.rplayer==[]){
                            fightMessage+='没有属性克制<br>'
                        }
                        fightMessage+=`${this.fightPokemen[0].name}最终的攻击力是${data.ss[0]}<br>
                        ${this.fightPokemen[1].name}最终的攻击力是${data.ss[1]}<br>`
                        
                        this.$notify({
                            title: '对战结果',
                            message: fightMessage ,
                            dangerouslyUseHTMLString: true,//使显示的字符为html字符
                        });
                        this.lflag = data.ss[0] > data.ss[1]
                        this.rflag = data.ss[0] < data.ss[1]
                    }
                }).catch(error=>{
                    console.log(error)
                })
            },
            clear(){
                this.fightPokemen = []
                this.lflag = false
                this.rflag = false
                this.vsflag = true
            },
            addFight(pokemen){
                if(this.fightPokemen.length==2){
                    this.$message.error("只能有两个对手!")
                }else if(this.fightPokemen.includes(pokemen)){//判断宝可梦是否重复
                    this.$message.error("选手不能重复！")
                }else{
                    this.fightPokemen.push(pokemen)
                }
            },
            attributesChange(e){
                this.pokemenForm.attributes = ''
                this.pokemenForm.attributes = this.attributesListChecked.join(',')
                this.$refs.pokemenForm.validateField('attributes')
                this.$refs.pokemenForm.validate(v=> {})
                
            },
            getAllAttribute(){
                getAllAttribute().then(resp=>{
                    if(resp.code == 200){
                        this.attributesList = resp.data
                    }
                })
            },
            getPokemenList(){
                getPokemenList(this.pageNumber, this.pageSize, this.queryForm).then(resp=>{
                    if(resp.code==200){
                        const data = resp.data
                        this.pokemenList = data.rows
                        this.pageNumber = data.pageNumber
                        this.pageSize = data.pageSize
                        this.total = data.total
                    }else{
                        this.pokemenList = []
                        this.pageNumber = 1
                        this.pageSize = 4
                        this.total = 0
                    }
                    
                })
                
            },
            
        },
        mounted() {
            this.getPokemenList()
            
        },
    }
</script>

<style scoped>
    .vs{
        width: 40vw;
        margin: auto;
        border-radius: 50px;
        /* margin-bottom: 20px; */
    }
    .box-card{
        display: flex;
        flex-direction: column;
    }
    .defeated{
        filter: grayscale(1);
    }
    .victoryl{
        box-shadow: 0 0 8px 2px yellowgreen;
        animation: fightAnimationLeft 1.2s;
    }
    .victoryr{
        box-shadow: 0 0 8px 2px yellowgreen;
        animation: fightAnimationRight 1.2s ;
        /* 调用动画 */
    }
    .hg{
        width: 25px;
        height: 25px;
        transform:rotate(30deg);
        background: url("../../../assets/hg.png");
        background-size:cover; 
        background-attachment:local;
        float: right;
        margin-top: -5px;
        margin-left: -30px;
    }
    /* 左宝可梦胜利动画 */
    @keyframes fightAnimationLeft {
        0%{
            transform: translate(0px, 0px);
        }
        50%{
            transform: translate(-450px, 0px);
        }
        100%{
            transform: translate(0px, 0px);
        }
    }
    @keyframes fightAnimationRight {
        0%{
            transform: translate(0px, 0px);
        }
        50%{
            transform: translate(450px, 0px);
        }
        100%{
            transform: translate(0px, 0px);     
        }
    }
</style>