import { defineStore } from 'pinia'

export const useLoadingStore = defineStore('loading', {
    state: () => ({
        count: 0,
        message: ''
    }),

    getters: {
        isLoading(state) {
            return state.count > 0;
        },
    },

    actions: {
        loading(msg) {
            this.count++;
            this.message = msg
        },
        unLoading() {
            if(this.count > 0) {
                this.count--;
            }
            if (this.count == 0) {
                this.message = ''
            }
        },
        reMsg(msg) {
            this.message = msg
        },
    }
})