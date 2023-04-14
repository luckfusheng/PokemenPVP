<template>
    <div>
        <el-card class="box-card">
            <el-row>
                <el-col :span="24">
                    <el-form ref="queryForm" :model="queryForm" inline size="mini" label-width="80px"> 
                        <el-form-item label="编号"  prop="number">
                            <el-input v-model="queryForm.number" placeholder="编号" 
                            @keyup.enter.native="search"></el-input>
                        </el-form-item>
                        <el-form-item label="名称"  prop="name">
                            <el-input v-model="queryForm.name" placeholder="名称" 
                            @keyup.enter.native="search"></el-input>
                        </el-form-item>
                        
                        <el-button size="mini" type="primary" @click="search">确定</el-button>
                        <el-button size="mini" type="info" @click="reset">重置</el-button>
                        <el-button size="mini" type="success" @click="addPokemen"
                            style="position:absolute;right:50px;">添加</el-button>
                    </el-form>
                </el-col>
            </el-row>
            <el-table :data="pokemenList" border stripe style="width=100%; margin:10px 0px" >
                <el-table-column label="编号" prop="number" align="center"  ></el-table-column>
                <el-table-column label="名称" prop="name" align="center"></el-table-column>
                <el-table-column label="头像" prop="url" align="center" width="100px">
                    <template slot-scope="{row}">
                        <el-image :src="upload_url+row.url" style="width: 40px; height: 40px;"
                            :preview-src-list="(upload_url+row.url).split()"></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="attributeList" align="center"  label="属性"  width="200px">
                    <template slot-scope="scope" >
                        <el-tag v-for="(tag,index) in scope.row.attributesList" :key="index" >{{tag.name}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="身高" prop="stature" align="center"></el-table-column>
                <el-table-column label="体重" prop="weight" align="center"></el-table-column>
                <el-table-column label="宝可梦特性" prop="characteristic" align="center"></el-table-column>
                <el-table-column label="操作" align="center">
                    <template slot-scope="{row}">
                        <el-button type="primary" icon="el-icon-edit" size="mini" @click="updatePokemen(row)">编辑</el-button>
                        <el-button type="dandanger" size="mini" @click="deletePokemen(row.id)" >删除</el-button>
                    </template>
                </el-table-column>
            
            </el-table>
            <el-pagination
                @size-change="pageSizeChange"
                @current-change="pageNumberChange"
                :current-page="pageNumber"
                :page-sizes="[4, 8, 16]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
            <!-- .sync 让子组件可以操作父组件数据 -->
            <el-dialog :title="title" :visible.sync="pokemenFormDialogVisible" :close-on-click-modal="false">
                <el-form ref="pokemenForm" :rules="FormRules" :model="pokemenForm" size="mini" label-width="80px">
                    <el-form-item label="编号" prop="number">
                        <el-input v-model="pokemenForm.number" placeholder="请输入编号" />
                    </el-form-item>

                    <el-form-item label="名称" prop="name">
                        <el-input v-model="pokemenForm.name" placeholder="请输入名称" />
                    </el-form-item>

                    <el-form-item label="战斗力" prop="ss">
                        <el-input  v-model="pokemenForm.ss" placeholder="请输入战斗力" />
                    </el-form-item>
                    <el-form-item label="属性" prop="attributes">
                        <el-checkbox-group  v-model="attributesListChecked" @change="attributesChange">
                            <el-checkbox  v-for="attributes in attributesList" :key="attributes.id" 
                            :label="attributes.id" >{{attributes.name}}</el-checkbox>
                        </el-checkbox-group>
                    </el-form-item>
                    <el-form-item label="身高" prop="stature">
                        <el-input  v-model="pokemenForm.stature" placeholder="请输入身高" >m</el-input>                   </el-form-item>
                    <el-form-item label="体重" prop="weight">
                        <el-input  v-model="pokemenForm.weight" placeholder="请输入体重" >kg</el-input>
                    </el-form-item>
                    <el-form-item label="头像" prop="url">
                        <el-upload :action="upload_url + '/upload/image'"
                        list-type="picture" :file-list="fileList"
                        :on-success="uploadSuccess" :on-remove="onRemove" 
                        :before-upload="beforeUpload" :limit="limit" :on-exceed="onExceed">
                            <el-button size="small" type="primariy" >点击上传</el-button>
                            <div slot="tip" >只能上传jpg/png文件，且不超过2MB</div>
                        </el-upload>
                    </el-form-item>
                    <el-form-item label="简介" prop="characteristic">
                        <el-input type="textarea" v-model="pokemenForm.characteristic" placeholder="请输入简介" />
                    </el-form-item>
                </el-form>
                <div slot="footer">
                    <el-button @click="pokemenFormDialogVisible=false">取 消</el-button>
                    <el-button type="primary" @click="submit(1)">确 定</el-button>
                </div>
            </el-dialog>
        </el-card>
    </div>
</template>

<script>
    import {addPokemen, getPokemenList, updatePokemens, deletePokemen  } from '@/api/manage';
    import { getAllAttribute } from '@/api/attribute';
    export default {
        data() {
            return {
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
                pageSize:4,
                limit:1,
                total:0,
                fileList:[],
                FormRules:{
                    number: [{required:true, message:'请输入编号'},
                            { pattern: /^(\d{1,3})(\.{0}|\.\d{1,2})$/, message: '只能是整数和两位小数' }],
                    name: [{required:true, message:'请输入名字'}],
                    ss: [{required:true, message:'请输入战力'},
                            { pattern: /^(\d{1,3})(\.{0}|\.\d{1,2})$/, message: '只能是整数和两位小数' }],
                    url: [{required:true, message:'请上传图片'}],
                    stature: [{required:true, message:'请输入身高'},
                            { pattern: /^(\d{1,3})(\.{0}|\.\d{1,2})$/, message: '只能是整数和两位小数' }],
                    weight: [{required:true, message:'请输入体重'},
                            { pattern: /^(\d{1,3})(\.{0}|\.\d{1,2})$/, message: '只能是整数和两位小数' }],
                    characteristic: [{required:true, message:'请输入描述'},
                                    {min:1,max:200,message:'只能输入1-200个字符'}],
                    attributes: [{required:true, message:'请选择属性'}],
                },
                title:'新添',

            }
        },
        methods: {
            attributesChange(e){
                this.pokemenForm.attributes=''
                this.pokemenForm.attributes = this.attributesListChecked.join(',')
                this.$refs.pokemenForm.validateField('attributes')
                
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
            addPokemen(){
                this.title='添加'
                this.pokemenFormDialogVisible=true
                this.$nextTick(()=>{ //unbeliveable
                    this.pokemenForm = {
                        id:'',
                        number:'',
                        name:'',
                        ss:'',
                        url:'',
                        stature:'',
                        weight:'',
                        characteristic:'',
                        attributes:'',
                    }
                    this.fileList = []
                    this.attributesListChecked = []
                    this.$refs.pokemenForm.resetFields()
                })
            },
            updatePokemen(item){
                this.title='修改'
                console.log(item)
                this.pokemenFormDialogVisible=true
                this.pokemenForm = {...item}
                this.attributesListChecked  = []
                item.attributesList.forEach(element => {
                    this.attributesListChecked.push(element.id)
                });
                // 直接遍历 把属性id的数组组合成字符串，用逗号分割，后端在分成数组，添加
                this.pokemenForm.attributes = this.attributesListChecked.join(',')
                
                this.fileList = [{url:this.upload_url + this.pokemenForm.url}]
                
            },
            reset(){
                this.$refs.queryForm.resetFields()
                this.search()
            },
            
            uploadSuccess(response, file, fileList){
                this.pokemenForm.url = response.data
                this.$refs.pokemenForm.validateField('url')

            },

            onRemove(){
                this.pokemenForm.url = ''
                this.fileList = [] 
                this.$refs.pokemenForm.validateField('url')

            },
            onExceed(file,fileList){
                this.$message.warning('只能上传' + this.limit + '张图片')
            },
            submit(flag){
                this.$refs.pokemenForm.validate(v=> {
                    if(v){//校验通过
                        if(!this.pokemenForm.id){//添加
                            addPokemen(this.pokemenForm).then(resp=>{
                                const {code,message} = resp
                                if(code === 200){
                                    this.$message.success("成功")
                                    this.$refs.pokemenForm.resetFields()
                                    this.getPokemenList()  
                                }else{
                                    this.$message.error(message)
                                }
                            })
                        }else{//更新
                            updatePokemens(this.pokemenForm).then(resp=>{
                                const { code, message } =  resp
                                if(code === 200){
                                    this.$message.success("成功")
                                    this.getPokemenList()                                                                                 
                                }else{
                                    this.$message.error(message)
                                }
                            })
                        }
                        this.fileList=[]
                        this.attributesListChecked = []
                        this.pokemenFormDialogVisible=false
                    }else{
                        return false
                    }
                })
            },
            pageSizeChange(v){
                this.pageSize = v
                this.getPokemenList()
            },

            pageNumberChange(v){
                this.pageNumber = v
                this.getPokemenList()
            },
            search(){
                this.pageNumber = 1
                this.getPokemenList()
            },

            //重置按钮
            resetQueryForm(){
                this.$refs.queryForm.resetFields()
                this.search()
            },
            //删除按钮
            async deletePokemen(id){
                this.$confirm('是否删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    deletePokemen(id).then(resp=>{
                        if(resp.code == 200){
                            this.$message.success("删除成功")
                            this.getPokemenList()
                        }else{
                            this.$message.error(resp.message)
                        }
                    }).catch(e=>{
                        this.$message.error("删除失败")
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });          
                });
            },
            beforeUpload(file){
                const isJPG = file.type === 'image/jpeg'
                const isPNG = file.type === 'image/png' 

                const isLt2MB = file.size / 1024 / 1024 < 2
                if(!isJPG && !isPNG ){
                    this.$message.warning('仅支持JPG和PNG格式')
                }
                if(!isLt2MB){
                    this.$message.warning('图片大小不能超过2MB')
                }
            },
        },
        mounted() {
            this.getPokemenList()
            this.getAllAttribute()
        },
    }
</script>

<style scoped>

</style>