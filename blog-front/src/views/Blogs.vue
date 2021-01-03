<template>
  <div class="blogList" v-on:click="handleTableClick">
    <BlogList
      species="1"
      :pets="[]"
    />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import { BlogList } from '@/components'
// import { getBlogList } from '@/api/data'

export default {
  components: {
      BlogList
  },
  data () {
    return {}
  },
  computed: {
    ...mapState([
      'cats'
    ])
  },
  methods: {
      ...mapActions(['FetchBlogList']),
      handleTableClick (e) {
          console.log('handleTableClick', e)
          console.log(this)
          this.FetchBlogList({})
              .then((res) => console.log(res))
              .finally(() => {
                  console.log('finish')
              })
      }

  }
}
</script>
